package com.example.side.service;


import com.example.side.model.entity.Community;
import com.example.side.repository.CommunityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class CommunityService{
    private final CommunityRepository communityRepository;
    @Transactional
    public void save(Community community){
        communityRepository.save(community);
    }
    //
    @Transactional
    public List<Community> findAll(){
        return communityRepository.findAll();
    }


}