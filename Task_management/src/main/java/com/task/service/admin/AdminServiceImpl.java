package com.task.service.admin;

import com.task.dto.TaskDto;
import com.task.dto.UserDto;
import com.task.entity.Task;
import com.task.entity.User;
import com.task.enums.TaskStatus;
import com.task.enums.UserRole;
import com.task.exception.ResourceNotFoundException;
import com.task.repository.TaskRepository;
import com.task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .filter(user -> user.getUserRole()== UserRole.EMPLOYEE)
                .map(User::getUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Optional<User> optionalUser = userRepository.findById(taskDto.getEmployeeId());
        if (optionalUser.isPresent()){
            Task task = new Task();
            task.setTitle(taskDto.getTitle());
            task.setDescription(taskDto.getDescription());
            task.setDueDate(taskDto.getDueDate());
            task.setPriority(taskDto.getPriority());
            task.setTaskStatus(TaskStatus.PENDING);
            task.setUser(optionalUser.get());
            Task createdTask = taskRepository.save(task);
            return createdTask.getTaskDto();
        }
        return null;
    }

    @Override
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Task::getDueDate).reversed())
                .map(Task::getTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("task", "id", id));
        taskRepository.delete(task);
    }

    @Override
    public TaskDto getTaskById(Long taskId) {
        return taskRepository.findById(taskId).map(Task::getTaskDto).orElseThrow(() -> new ResourceNotFoundException("task", "id", taskId));
    }

    @Override
    public TaskDto updateTask(Long taskId, TaskDto taskDto) {
        return null;
    }
}
