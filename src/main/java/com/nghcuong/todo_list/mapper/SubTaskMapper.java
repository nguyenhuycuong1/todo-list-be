package com.nghcuong.todo_list.mapper;

import com.nghcuong.todo_list.dto.SubTaskDTO;
import com.nghcuong.todo_list.entity.SubTask;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubTaskMapper {
    SubTask toEntity(SubTaskDTO subTaskDTO);
    SubTaskDTO toDTO(SubTask subTask);
    List<SubTaskDTO> toDTOList(List<SubTask> subTasks);
}
