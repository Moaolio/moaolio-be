package com.example.side.service;

import com.example.side.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserService{
    Iterable<User> selectAll();
    Optional<User> selectOneById(Long id);
    User insertUser(User user);
    void updateUser(User user);
    void deleteUser(Long id);

}
