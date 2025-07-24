package com.nghcuong.todo_list.mapper;

import com.nghcuong.todo_list.dto.UserDTO;
import com.nghcuong.todo_list.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDTO userDTO);
    UserDTO toDTO(User user);
    List<UserDTO> toDTOList(List<User> users);
}
