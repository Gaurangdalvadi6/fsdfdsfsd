package com.listshow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.listshow.dto.UserDto;

@Service
public interface UserService {

	UserDto createUser(UserDto userDto);
	
	List<UserDto> getAllUser();
	
	UserDto getUserById(Long userId);
	
	UserDto updateUserById(UserDto userDto,Long userId);
	
	void deleteUserById(Long userId);
}
