package com.listshow.service.impl;

import org.springframework.stereotype.Service;

import com.listshow.dto.UserDto;
import com.listshow.model.User;

@Service
public class UserMapper {

	public User toUser(UserDto userDto) {
		var user = new User();
		user.setName(userDto.name());
		user.setEmail(userDto.email());
		user.setMessage(userDto.message());
		return user;
	}
	
	public UserDto toUserDto(User user) {
		return new UserDto(user.getId(), user.getName(), user.getEmail(), user.getMessage());
	}
}
