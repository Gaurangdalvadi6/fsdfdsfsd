package com.movieflix.controller;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieflix.auth.entity.ForgetPassword;
import com.movieflix.auth.entity.User;
import com.movieflix.auth.repository.ForgetPasswordRepository;
import com.movieflix.auth.repository.UserRepository;
import com.movieflix.auth.utils.ChangePassword;
import com.movieflix.dto.MailBody;
import com.movieflix.service.EmailService;

@RestController
@RequestMapping("/forgetPassword")
public class ForgetPasswordController {

	private final UserRepository userRepository;
	
	private final EmailService emailService;
	
	private final ForgetPasswordRepository forgetPasswordRepository;
	
	private final PasswordEncoder passwordEncoder;

	public ForgetPasswordController(UserRepository userRepository, EmailService emailService, ForgetPasswordRepository forgetPasswordRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.emailService = emailService;
		this.forgetPasswordRepository = forgetPasswordRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/verifyMail/{email}")
	public ResponseEntity<String> verifyMail(@PathVariable String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Please provide an valid email!"));
		
		int otp = otpGenerate();
		MailBody mailBody = MailBody.builder()
							.to(email)
							.text("This is The OTP for your Forget Password Request : "+otp)
							.subject("OTP for forget Password Request")
							.build();
		
		ForgetPassword forgetPassword = ForgetPassword.builder()
										.otp(otp)
										.expirationTime(new Date(System.currentTimeMillis()+(5*60*1000)))
										.user(user)
										.build();
		emailService.sendSimpleMessage(mailBody);
		
		forgetPasswordRepository.save(forgetPassword);
		
		return ResponseEntity.ok("Email sent for verfication!");
	}
	
	@PostMapping("/verifyOtp/{otp}/{email}")
	public ResponseEntity<String> verifyOtp(@PathVariable Integer otp,@PathVariable String email){
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Please provide an valid email!"));
		
		ForgetPassword fp = forgetPasswordRepository.findByOtpAndUser(otp, user).orElseThrow(() -> new RuntimeException("Invalid OTP for email: "+email));
		
		if (fp.getExpirationTime().before(Date.from(Instant.now()))) {
			forgetPasswordRepository.deleteById(fp.getFpid());
			return new ResponseEntity<>("OTP has expired! ",HttpStatus.EXPECTATION_FAILED);
		}
		
		return ResponseEntity.ok("OTP verified!");
	}
	
	@PostMapping("/changePassword/{email}")
	public ResponseEntity<String> changePasswordHandler(@RequestBody ChangePassword changePassword,
														@PathVariable String email){
		if (!Objects.equals(changePassword.password(), changePassword.repeatPassword())) {
			return new ResponseEntity<>("Please enter the password again!",HttpStatus.EXPECTATION_FAILED);
		}
		
		String encodedPassword = passwordEncoder.encode(changePassword.password());
		
		userRepository.updatePassword(email, encodedPassword);
		
		return ResponseEntity.ok("Password has been changed!");
	}
	
	private Integer otpGenerate() {
		Random random = new Random();
		return random.nextInt(100_000, 999_999);
	}
}
