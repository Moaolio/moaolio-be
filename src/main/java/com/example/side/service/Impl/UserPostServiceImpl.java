package com.example.side.service.Impl;

import com.example.side.model.entity.UserPost;
import com.example.side.repository.UserPostRepository;
import com.example.side.service.UserPostService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserPostServiceImpl implements UserPostService {
    @Autowired
    UserPostRepository userPostRepository;

    public Iterable<UserPost> selectAll() {
        return userPostRepository.findAll();
    }

    @Override
    public Optional<UserPost> selectOneById(Long id) {
        return userPostRepository.findById(id);
    }

    @Override
    public UserPost insertUser(UserPost user) {
        return userPostRepository.save(user);
    }
    @Override
    public void deleteUser(Long id) {
        userPostRepository.deleteById(id);
    }
    @Override
    public void updateUser(UserPost user) {
        userPostRepository.save(user);
    }
}
