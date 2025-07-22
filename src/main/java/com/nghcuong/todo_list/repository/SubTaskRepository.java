package com.nghcuong.todo_list.repository;

import com.nghcuong.todo_list.dto.SubTaskDTO;
import com.nghcuong.todo_list.entity.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubTaskRepository extends JpaRepository<SubTask, Long> {

    List<SubTask> findByTaskId(Long taskId);


}
