package com.graphql.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.graphql.entities.User;
import com.graphql.exception.ExceptionHandler;
import com.graphql.repositories.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	// craeting user
	public User create(User user) {
		return userRepository.save(user);
	}
	
	// get all user
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
	
	// get single user
	public User getUser(Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(ExceptionHandler::throwResourceNotFoundException);
		return user;
	}
	// updating user
	public User updateUser(User user,Integer userId) {
		User user2 = userRepository.findById(userId).orElseThrow(ExceptionHandler::throwResourceNotFoundException);
		user2.setName(user.getName());
		user2.setEmail(user.getEmail());
		user2.setPhone(user.getPhone());
		user2.setPassword(user.getPassword());
		userRepository.save(user2);
		return user2;
	}
	
	// delete user
	public boolean deleteUser(Integer usetId) {
		User user = userRepository.findById(usetId).orElseThrow(ExceptionHandler::throwResourceNotFoundException);
		userRepository.delete(user);
		return true;
	}
}
