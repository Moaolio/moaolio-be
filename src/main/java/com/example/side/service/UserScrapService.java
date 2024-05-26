package com.example.side.service;

import com.example.side.model.entity.UserScrap;

import java.util.Optional;

public interface UserScrapService {
    Iterable<UserScrap> selectAll();
    Optional<UserScrap> selectOneById(Long id);
    UserScrap insertUserScrap(UserScrap userScrap);
    void updateUserScrap(UserScrap userScrap);
    void deleteUserScrap(Long id);
}