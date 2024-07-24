package com.task.service.admin;

import com.task.dto.UserDto;

import java.util.List;

public interface AdminService {

    List<UserDto> getUsers();
}
