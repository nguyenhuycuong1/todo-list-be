package com.nghcuong.todo_list.controllers;

import com.nghcuong.todo_list.dto.SubTaskDTO;
import com.nghcuong.todo_list.dto.response.ApiResponse;
import com.nghcuong.todo_list.mapper.SubTaskMapper;
import com.nghcuong.todo_list.services.SubTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sub-tasks")
public class SubTaskController {
    @Autowired
    private SubTaskService subTaskService;

    @Autowired
    private SubTaskMapper subTaskMapper;

    @GetMapping("/get-all")
    public ApiResponse<List<SubTaskDTO>> getAllSubTasks() {
        List<SubTaskDTO> subTasks = subTaskMapper.toDtoList(subTaskService.findAll());
        return new ApiResponse<>(subTasks).success();
    }

    @GetMapping("/get-by-id/{id}")
    public ApiResponse<SubTaskDTO> getSubTaskById(@PathVariable Long id) {
        SubTaskDTO subTask = subTaskMapper.toDto(subTaskService.findById(id));
        return new ApiResponse<>(subTask).success();
    }

    @PostMapping("/create")
    public ApiResponse<SubTaskDTO> createSubTask(@RequestBody SubTaskDTO subTaskDTO) {
        SubTaskDTO createdSubTask = subTaskMapper.toDto(subTaskService.save(subTaskMapper.toEntity(subTaskDTO)));
        return new ApiResponse<>(createdSubTask).success();
    }

    @PutMapping("/update/{id}")
    public ApiResponse<SubTaskDTO> updateSubTask(@PathVariable Long id, @RequestBody SubTaskDTO subTaskDTO) {
        SubTaskDTO updatedSubTask = subTaskMapper.toDto(subTaskService.update(id, subTaskMapper.toEntity(subTaskDTO)));
        return new ApiResponse<>(updatedSubTask).success();
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<?> deleteSubTask(@PathVariable Long id) {
        subTaskService.deleteById(id);
        return new ApiResponse<Void>().success();
    }

    @GetMapping("/get-by-task-id/{taskId}")
    public ApiResponse<List<SubTaskDTO>> getSubTasksByTaskId(@PathVariable Long taskId) {
        List<SubTaskDTO> subTasks = subTaskMapper.toDtoList(subTaskService.findByTaskId(taskId));
        return new ApiResponse<>(subTasks).success();
    }
}
