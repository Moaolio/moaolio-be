package com.example.side.service.Impl;

import com.example.side.model.entity.UserScrap;
import com.example.side.repository.UserScrapRepository;
import com.example.side.service.UserScrapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserScrapServiceImpl implements UserScrapService {
    @Autowired
    UserScrapRepository userScrapRepository;
    @Override
    public Iterable<UserScrap> selectAll() {
        return null;
    }

    @Override
    public Optional<UserScrap> selectOneById(Long id) {
        return Optional.empty();
    }

    @Override
    public UserScrap insertUserScrap(UserScrap userScrap) {
        return null;
    }

    @Override
    public void updateUserScrap(UserScrap userScrap) {

    }

    @Override
    public void deleteUserScrap(Long id) {

    }
}
