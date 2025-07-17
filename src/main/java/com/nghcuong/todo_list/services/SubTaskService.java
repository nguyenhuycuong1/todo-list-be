package com.nghcuong.todo_list.services;

import com.nghcuong.todo_list.entity.SubTask;

import java.util.List;

public interface SubTaskService extends IGenericService<SubTask, Long> {

    List<SubTask> findByTaskId(Long taskId);
}
