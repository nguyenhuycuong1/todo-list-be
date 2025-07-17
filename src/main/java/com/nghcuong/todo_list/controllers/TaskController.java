package com.nghcuong.todo_list.controllers;

import com.nghcuong.todo_list.dto.TaskDTO;
import com.nghcuong.todo_list.dto.response.ApiResponse;
import com.nghcuong.todo_list.entity.Task;
import com.nghcuong.todo_list.mapper.TaskMapper;
import com.nghcuong.todo_list.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping("/get-all")
    public ApiResponse<List<TaskDTO>> getAllTasks() {
        List<Task> tasks = taskService.findAll();
        List<TaskDTO> taskDTOs = taskMapper.toDTOs(tasks);
        return new ApiResponse<List<TaskDTO>>(taskDTOs).success();
    }

    @GetMapping("get-by-id/{id}")
    public ApiResponse<TaskDTO> getTaskById(@PathVariable Long id) {
        Task task = taskService.findById(id);
        TaskDTO taskDTO = taskMapper.toDTO(task);
        return new ApiResponse<TaskDTO>(taskDTO).success();
    }

    @PostMapping("/create")
    public ApiResponse<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        Task createdTask = taskService.save(taskMapper.toEntity(taskDTO));
        TaskDTO createdTaskDTO = taskMapper.toDTO(createdTask);
        return new ApiResponse<TaskDTO>(createdTaskDTO).success();
    }

    @PutMapping("/update/{id}")
    public ApiResponse<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        Task updatedTask = taskService.update(id, taskMapper.toEntity(taskDTO));
        TaskDTO updatedTaskDTO = taskMapper.toDTO(updatedTask);
        return new ApiResponse<TaskDTO>(updatedTaskDTO).success();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<?> deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return new ApiResponse<>().success();
    }

    @GetMapping("/get-by-category/{categoryId}")
    public ApiResponse<List<TaskDTO>> getTasksByCategoryId(@PathVariable Long categoryId) {
        List<Task> tasks = taskService.findByCategoryId(categoryId);
        List<TaskDTO> taskDTOs = taskMapper.toDTOs(tasks);
        return new ApiResponse<List<TaskDTO>>(taskDTOs).success();
    }

    @GetMapping("/quick-filter/{keyword}")
    public ApiResponse<List<TaskDTO>> quickFilterTasks(@PathVariable String keyword) {
        List<Task> tasks = taskService.quickFilter(keyword);
        List<TaskDTO> taskDTOs = taskMapper.toDTOs(tasks);
        return new ApiResponse<List<TaskDTO>>(taskDTOs).success();
    }
}
