package com.nghcuong.todo_list.services;

import com.nghcuong.todo_list.entity.Task;

import java.util.List;


public interface TaskService extends IGenericService<Task, Long> {
    List<Task> findByCategoryId(Long categoryId);

    List<Task> quickFilter(String keyword);
}
