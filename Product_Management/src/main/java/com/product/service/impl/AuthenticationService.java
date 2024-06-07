package com.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.product.exception.ApiException;
import com.product.repository.SellerRepo;
import com.product.security.AuthenticationRequest;
import com.product.security.AuthenticationResponse;
import com.product.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	@Autowired
	private final SellerRepo sellerRepo;
	
	@Autowired
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	private final JwtService jwtService;
	
	@Autowired
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
			var user = sellerRepo.findByEmail(request.getEmail()).orElseThrow();
			var jwtToken = jwtService.generateToken(user);
			return AuthenticationResponse.builder().token(jwtToken).build();
		} catch (BadCredentialsException  e) {
			throw new ApiException("Invalid Username And Password");
		}
		
	}
}
