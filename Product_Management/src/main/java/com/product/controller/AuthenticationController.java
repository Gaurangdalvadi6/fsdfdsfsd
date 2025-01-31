package com.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.payload.SellerDto;
import com.product.security.AuthenticationRequest;
import com.product.security.AuthenticationResponse;
import com.product.service.SellerService;
import com.product.service.impl.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	@Autowired
	private final AuthenticationService service;
	
	@Autowired
	private SellerService sellerService;
	
	// login user
		@PostMapping("/login")
		  public ResponseEntity<AuthenticationResponse> authenticate(
		      @RequestBody AuthenticationRequest request
		  ) {
		    return ResponseEntity.ok(service.authenticate(request));
		  }
		
		// register new user
		
		@PostMapping("/register")
		public ResponseEntity<SellerDto> registerUser(@RequestBody SellerDto sellerDto){
			SellerDto registerUser = this.sellerService.registerNewSeller(sellerDto);
			return new ResponseEntity<SellerDto>(registerUser,HttpStatus.CREATED);
		}
}
