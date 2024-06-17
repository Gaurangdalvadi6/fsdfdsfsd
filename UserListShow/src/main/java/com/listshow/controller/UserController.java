package com.listshow.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.listshow.dto.UserDto;
import com.listshow.exception.ApiResponse;
import com.listshow.service.impl.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/Users")
public class UserController {

	public final UserServiceImpl userServiceImpl;

	public UserController(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto dto = this.userServiceImpl.createUser(userDto);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUser(){
		List<UserDto> allUser = this.userServiceImpl.getAllUser();
		return new ResponseEntity<>(allUser,HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long userId){
		UserDto userById = this.userServiceImpl.getUserById(userId);
		return new ResponseEntity<>(userById,HttpStatus.OK);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Long userId){
		UserDto updateUserById = this.userServiceImpl.updateUserById(userDto, userId);
		return new ResponseEntity<>(updateUserById,HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}")
	public ApiResponse deleteProduct(@PathVariable Long userId) {
		this.userServiceImpl.deleteUserById(userId);
		return new ApiResponse("deleted successfully", true);
	}
}
