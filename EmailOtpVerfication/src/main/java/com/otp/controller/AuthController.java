package com.otp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.otp.model.User;
import com.otp.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
    private UserService userService;
	
	@PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.registerUser(user.getEmail(), user.getPassword());
        return ResponseEntity.ok("Registration successful. Please check your email for the OTP.");
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");
        if (userService.verifyUser(email, otp)) {
            return ResponseEntity.ok("Verification successful. You can now log in.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP or OTP expired.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");
        if (userService.authenticateUser(email, otp)) {
            return ResponseEntity.ok("Login successful.");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials or user not verified.");
    }
}
