package com.otp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otp.model.User;
import com.otp.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private OtpService otpService;
	
	public void registerUser(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password); // Ideally, hash the password
        user.setEnabled(false);
        userRepo.save(user);
        otpService.generateOtp(email);
    }
	
	public boolean verifyUser(String email, String otp) {
        if (otpService.verifyOtp(email, otp)) {
            User user = userRepo.findByEmail(email);
            user.setEnabled(true);
            userRepo.save(user);
            return true;
        }
        return false;
    }
	
	public boolean authenticateUser(String email, String otp) {
        if (otpService.verifyOtp(email, otp)) {
            User user = userRepo.findByEmail(email);
            return user != null && user.isEnabled();
        }
        return false;
    }
}
