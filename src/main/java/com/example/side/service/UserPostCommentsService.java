package com.example.side.service;

import com.example.side.model.entity.UserPostComments;

import java.util.Optional;

public interface UserPostCommentsService {
    Iterable<UserPostComments> selectAll();
    Optional<UserPostComments> selectOneById(Long id);
    UserPostComments insertUserPostComments(UserPostComments userPostComments);
    void updateUserPostComments(UserPostComments userPostComments);
    void deleteUserPostComments(Long id);

}