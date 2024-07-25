package com.task.service.admin;

import com.task.dto.TaskDto;
import com.task.dto.UserDto;

import java.util.List;

public interface AdminService {

    List<UserDto> getUsers();

    TaskDto createTask(TaskDto taskDto);

    List<TaskDto> getAllTasks();

    void deleteTask(Long id);

    TaskDto getTaskById(Long taskId);

    TaskDto updateTask(Long taskId,TaskDto taskDto);
}
