package com.example.side.service.Impl;

import com.example.side.model.entity.User;
import com.example.side.repository.UserRepository;
import com.example.side.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public Iterable<User> selectAll() {
        return userRepository.findAll();
    }


    @Override
    public Optional<User> selectOneById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User insertUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }
}
