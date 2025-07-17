package com.nghcuong.todo_list.mapper;

import com.nghcuong.todo_list.dto.TaskDTO;
import com.nghcuong.todo_list.entity.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toEntity(TaskDTO taskDTO);
    TaskDTO toDTO(Task task);
    List<TaskDTO> toDTOs(List<Task> tasks);
}
