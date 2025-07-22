package com.nghcuong.todo_list.repository;

import com.nghcuong.todo_list.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM app_user WHERE username = :username", nativeQuery = true)
    User findByUsername(@Param("username") String username);
    @Query(value = "SELECT * FROM app_user WHERE email = :email", nativeQuery = true)
    User findByEmail(@Param("email") String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
