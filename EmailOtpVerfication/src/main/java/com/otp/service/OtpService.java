package com.otp.service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otp.model.Otp;
import com.otp.repository.OtpRepository;

@Service
public class OtpService {

	@Autowired
	private OtpRepository otpRepository;
	
	@Autowired
	private EmailService emailService;
	
	
	    
		public static String generateRandomString(int length) {
	        return new Random().ints(48, 58)
	                           .limit(length)
	                           .mapToObj(i -> (char) i)
	                           .map(Object::toString)
	                           .collect(Collectors.joining());
	    }
	
	public String generateOtp(String email) {
        String otp = generateRandomString(6);
        Otp otpEntity = new Otp();
        otpEntity.setEmail(email);
        otpEntity.setOtp(otp);
        otpEntity.setExpiryDate(LocalDateTime.now().plusMinutes(5));
        otpRepository.save(otpEntity);
        emailService.sendOtpEmail(email, otp);
        return otp;
    }
	
	public boolean verifyOtp(String email,String otp) {
		Otp otpEntity = otpRepository.findByEmailAndOtp(email, otp);
		if (otpEntity != null && otpEntity.getExpiryDate().isAfter(LocalDateTime.now())) {
			return true;
		}
		return false;
	}
}
