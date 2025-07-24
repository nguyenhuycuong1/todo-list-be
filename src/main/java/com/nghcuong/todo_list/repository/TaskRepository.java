package com.nghcuong.todo_list.repository;

import com.nghcuong.todo_list.entity.Task;
import com.nghcuong.todo_list.enums.Priority;
import com.nghcuong.todo_list.enums.TaskStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByCategoryId(Long categoryId);

    @Query(value = "SELECT * FROM task t WHERE t.due_date = :dueDate AND t.user_id = :userId", nativeQuery = true)
    List<Task> findTasksByDueDate(@Param("dueDate") LocalDate dueDate, @Param("userId") Long userId);

    @Query("SELECT t FROM Task t WHERE t.priority = :priority AND t.userId = :userId")
    List<Task> findTasksByPriority(@Param("priority") Priority priority, @Param("userId") Long userId);

    @Query("SELECT t FROM Task t WHERE t.status = :status AND t.userId = :userId")
    List<Task> findTasksByStatus(@Param("status") TaskStatus status, @Param("userId") Long userId);

    List<Task> findByUserId(Long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE task SET status = :#{#status.name()} WHERE id = :id", nativeQuery = true)
    int updateTaskStatusNative(@Param("id") Long id, @Param("status") TaskStatus status);
}
