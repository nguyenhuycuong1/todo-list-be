package com.nghcuong.todo_list.services.impl;

import com.nghcuong.todo_list.entity.Task;
import com.nghcuong.todo_list.enums.Priority;
import com.nghcuong.todo_list.enums.TaskStatus;
import com.nghcuong.todo_list.repository.TaskRepository;
import com.nghcuong.todo_list.services.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TaskServiceImpl extends GenericServiceImpl<Task, Long> implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        super(taskRepository);
        this.taskRepository = taskRepository;
    }

    public List<Task> findByCategoryId(Long categoryId) {
        return taskRepository.findByCategoryId(categoryId);
    }

    public List<Task> quickFilter(String keyword) {
        if (Objects.equals(keyword, "all-tasks")) {
            return taskRepository.findAll();
        } else if (Objects.equals(keyword, "due-today")) {
            return findTaskDueToday();
        } else if (Objects.equals(keyword, "high-priority")) {
            return findTaskByPriority(Priority.HIGH);
        } else if (Objects.equals(keyword, "completed")) {
            return findTaskByStatus(TaskStatus.DONE);
        } else if (Objects.equals(keyword, "in-progress")) {
            return findTaskByStatus(TaskStatus.IN_PROGRESS);
        } else {
            return new ArrayList<>();
        }
    }

    private List<Task> findTaskDueToday() {
        LocalDate today = LocalDate.now();
        return taskRepository.findTasksByDueDate(today);
    }

    private List<Task> findTaskByPriority(Priority priority) {
        return taskRepository.findTasksByPriority(priority);
    }

    private List<Task> findTaskByStatus(TaskStatus taskStatus) {
        return taskRepository.findTasksByStatus(taskStatus);
    }

}
