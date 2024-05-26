package com.example.side.service;


import com.example.side.model.entity.Community;

import java.util.Optional;

public interface CommunityService{
    Iterable<Community> selectAll();
    Optional<Community> selectOneById(Long id);
}