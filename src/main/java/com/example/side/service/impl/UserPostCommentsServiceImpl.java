package com.example.side.service.impl;

import com.example.side.model.entity.UserPostComments;
import com.example.side.repository.UserPostCommentsRepository;
import com.example.side.service.UserPostCommentsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPostCommentsServiceImpl implements UserPostCommentsService {

    private final UserPostCommentsRepository userPostCommentsRepository;

    public UserPostCommentsServiceImpl(UserPostCommentsRepository userPostCommentsRepository) {
        this.userPostCommentsRepository = userPostCommentsRepository;
    }

    public Iterable<UserPostComments> selectAll() {
        return userPostCommentsRepository.findAll();
    }
    @Override
    public Optional<UserPostComments> selectOneById(Long id) {
        return userPostCommentsRepository.findById(id);
    }
    @Override
    public UserPostComments insertUserPostComments(UserPostComments userPostComments) {
        return userPostCommentsRepository.save(userPostComments);
    }
    @Override
    public void deleteUserPostComments(Long id) {
        userPostCommentsRepository.deleteById(id);
    }
    @Override
    public void updateUserPostComments(UserPostComments userPostComments) {
        userPostCommentsRepository.save(userPostComments);
    }
}
