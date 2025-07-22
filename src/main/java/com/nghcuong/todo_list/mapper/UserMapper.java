package com.nghcuong.todo_list.mapper;

import com.nghcuong.todo_list.dto.UserDTO;
import com.nghcuong.todo_list.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserDTO userDTO);
    UserDTO toUserDTO(User user);
}
