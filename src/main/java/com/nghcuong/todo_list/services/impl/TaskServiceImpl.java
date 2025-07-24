package com.nghcuong.todo_list.services.impl;

import com.nghcuong.todo_list.config.JwtProvider;
import com.nghcuong.todo_list.dto.TaskDTO;
import com.nghcuong.todo_list.entity.Task;
import com.nghcuong.todo_list.enums.Priority;
import com.nghcuong.todo_list.enums.TaskStatus;
import com.nghcuong.todo_list.repository.TaskRepository;
import com.nghcuong.todo_list.services.TaskService;
import com.nghcuong.todo_list.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class TaskServiceImpl extends GenericServiceImpl<Task, Long> implements TaskService {



    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        super(taskRepository);
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findByUserId(UserUtils.getCurrentUserId());
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
            log.info("checking completed tasks");
            return findTaskByStatus(TaskStatus.DONE);
        } else if (Objects.equals(keyword, "in-progress")) {
            return findTaskByStatus(TaskStatus.IN_PROGRESS);
        } else {
            return new ArrayList<>();
        }
    }

    private List<Task> findTaskDueToday() {
        LocalDate today = LocalDate.now();
        return taskRepository.findTasksByDueDate(today, UserUtils.getCurrentUserId());
    }

    private List<Task> findTaskByPriority(Priority priority) {
        return taskRepository.findTasksByPriority(priority, UserUtils.getCurrentUserId());
    }

    private List<Task> findTaskByStatus(TaskStatus taskStatus) {
        log.info("Finding tasks by status: {}", taskStatus);
        return taskRepository.findTasksByStatus(taskStatus, UserUtils.getCurrentUserId());
    }

    public int updateTaskStatusNative(Long id, TaskStatus status) {
        return taskRepository.updateTaskStatusNative(id, status);
    }

}
