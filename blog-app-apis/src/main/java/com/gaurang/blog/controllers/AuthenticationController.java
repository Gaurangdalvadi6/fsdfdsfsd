package com.gaurang.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaurang.blog.payloads.UserDto;
import com.gaurang.blog.services.AuthenticationService;
import com.gaurang.blog.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	@Autowired
	private final AuthenticationService service;
	
	@Autowired
	private UserService userService;
	
	// login user
	@PostMapping("/login")
	  public ResponseEntity<AuthenticationResponse> authenticate(
	      @RequestBody AuthenticationRequest request
	  ) {
	    return ResponseEntity.ok(service.authenticate(request));
	  }
	
	// register new user
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
		UserDto registerUser = this.userService.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(registerUser,HttpStatus.CREATED);
	}
	
	
}
