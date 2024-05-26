package com.example.side.service.Impl;

import com.example.side.model.entity.Community;
import com.example.side.repository.CommunityRepository;
import com.example.side.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    CommunityRepository communityRepository;

    @Override
    public Iterable<Community> selectAll() {
        return communityRepository.findAll();
    }

    @Override
    public Optional<Community> selectOneById(Long id) {
        return communityRepository.findById(id);
    }
}
