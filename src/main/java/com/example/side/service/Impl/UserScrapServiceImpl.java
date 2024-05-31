package com.example.side.service.Impl;

import com.example.side.model.entity.UserScrap;
import com.example.side.repository.UserScrapRepository;
import com.example.side.service.UserScrapService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserScrapServiceImpl implements UserScrapService {

    private final UserScrapRepository userScrapRepository;

    // Constructor injection for better testability and immutability
    public UserScrapServiceImpl(UserScrapRepository userScrapRepository) {
        this.userScrapRepository = userScrapRepository;
    }

    @Override
    public Iterable<UserScrap> selectAll() {
        return userScrapRepository.findAll();
    }

    @Override
    public Optional<UserScrap> selectOneById(Long id) {
        return userScrapRepository.findById(id);
    }

    @Override
    public UserScrap insertUserScrap(UserScrap userScrap) {
        return userScrapRepository.save(userScrap);
    }

    @Override
    public void deleteUserScrap(Long id) {
        userScrapRepository.deleteById(id);
    }
}
