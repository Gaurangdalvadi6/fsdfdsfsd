package com.task.controller.admin;

import com.task.dto.TaskDto;
import com.task.dto.UserDto;
import com.task.payload.APIResponse;
import com.task.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok(adminService.getUsers());
    }

    @PostMapping("/task")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto){
        TaskDto createdTaskDto = adminService.createTask(taskDto);
        if (createdTaskDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTaskDto);
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        return  ResponseEntity.status(HttpStatus.OK).body(adminService.getAllTasks());
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<APIResponse<Void>> deleteTask(@PathVariable Long id){
        adminService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<Void>(null,"Deleted SuccessFully",HttpStatus.OK));
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long taskId){
        return ResponseEntity.ok(adminService.getTaskById(taskId));
    }
}
