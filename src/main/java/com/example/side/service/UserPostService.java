package com.example.side.service;

import com.example.side.model.entity.UserPost;

import java.util.Optional;

public interface UserPostService {
    Iterable<UserPost> selectAll();
    Optional<UserPost> selectOneById(Long id);
    UserPost insertUser(UserPost user);
    void updateUser(UserPost user);
    void deleteUser(Long id);

}