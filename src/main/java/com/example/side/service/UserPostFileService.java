package com.example.side.service;

import com.example.side.model.entity.UserPostFile;

import java.util.Optional;

public interface UserPostFileService {
    Iterable<UserPostFile> selectAll();
    Optional<UserPostFile> selectOneById(Long id);
    UserPostFile insertUserPostFile(UserPostFile userPostFile);
    void updateUserPostFile(UserPostFile userPostFile);
    void deleteUserPostFile(Long id);
}
