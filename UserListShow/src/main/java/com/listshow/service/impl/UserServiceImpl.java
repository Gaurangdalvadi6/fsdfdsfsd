package com.listshow.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.listshow.dto.UserDto;
import com.listshow.exception.ResourceNotFoundException;
import com.listshow.model.User;
import com.listshow.repository.UserRepository;
import com.listshow.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	public final UserRepository userRepository;

	public final UserMapper userMapper;

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		var user = userMapper.toUser(userDto);
		var save = userRepository.save(user);
		return userMapper.toUserDto(save);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> list = this.userRepository.findAll();
		List<UserDto> userDtoList = list.stream().map(userMapper::toUserDto).collect(Collectors.toList());
		return userDtoList;
	}

	@Override
	public UserDto getUserById(Long userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", "Id ", userId));
		UserDto userDto = this.userMapper.toUserDto(user);
		return userDto;
	}

	@Override
	public UserDto updateUserById(UserDto userDto, Long userId) {
		User getUser = this.userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User ", "Id ", userId));

		getUser.setId(userId);
		getUser.setName(userDto.name());
		getUser.setEmail(userDto.email());
		getUser.setMessage(userDto.message());

		User updateUser = this.userRepository.save(getUser);
		UserDto dto = this.userMapper.toUserDto(updateUser);
		return dto;
	}

	@Override
	public void deleteUserById(Long userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", "Id ", userId));
		this.userRepository.delete(user);

	}

}
