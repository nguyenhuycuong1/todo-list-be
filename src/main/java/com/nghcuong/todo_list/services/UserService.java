package com.nghcuong.todo_list.services;

import com.nghcuong.todo_list.entity.User;

public interface UserService extends IGenericService<User, Long> {

    User findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void updatePassword(Long userId, String newPassword);

}
