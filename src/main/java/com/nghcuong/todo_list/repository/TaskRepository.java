package com.nghcuong.todo_list.repository;

import com.nghcuong.todo_list.entity.Task;
import com.nghcuong.todo_list.enums.Priority;
import com.nghcuong.todo_list.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByCategoryId(Long categoryId);

    List<Task> findTasksByDueDate(LocalDate dueDate);

    List<Task> findTasksByPriority(Priority priority);

    List<Task> findTasksByStatus(TaskStatus status);
}
