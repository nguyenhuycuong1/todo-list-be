package com.nghcuong.todo_list.services.impl;

import com.nghcuong.todo_list.entity.SubTask;
import com.nghcuong.todo_list.exception.AppException;
import com.nghcuong.todo_list.exception.ErrorCode;
import com.nghcuong.todo_list.repository.SubTaskRepository;
import com.nghcuong.todo_list.services.SubTaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubTaskServiceImpl extends GenericServiceImpl<SubTask, Long> implements SubTaskService {
    private final SubTaskRepository subTaskRepository;

    public SubTaskServiceImpl(SubTaskRepository subTaskRepository) {
        super(subTaskRepository);
        this.subTaskRepository = subTaskRepository;
    }

    public List<SubTask> findByTaskId(Long taskId) {
        if (taskId == -1) {
            throw new AppException(ErrorCode.RESOURCE_NOT_FOUND, ErrorCode.RESOURCE_NOT_FOUND.getMessage());
        }
        return subTaskRepository.findByTaskId(taskId);
    }
}
