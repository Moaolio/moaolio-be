package com.example.side.service.Impl;

import com.example.side.model.entity.UserPostFile;
import com.example.side.repository.UserPostFileRepository;
import com.example.side.service.UserPostFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPostFileServiceImpl implements UserPostFileService {
    @Autowired
    UserPostFileRepository userPostFileRepository;

    public Iterable<UserPostFile> selectAll() {
        return userPostFileRepository.findAll();
    }

    @Override
    public Optional<UserPostFile> selectOneById(Long id) {
        return userPostFileRepository.findById(id);
    }

    @Override
    public UserPostFile insertUserPostFile(UserPostFile userPostFile) {
        return userPostFileRepository.save(userPostFile);
    }
    @Override
    public void deleteUserPostFile(Long id) {
        userPostFileRepository.deleteById(id);
    }
    @Override
    public void updateUserPostFile(UserPostFile userPostFile) {
        userPostFileRepository.save(userPostFile);
    }
}
