package com.example.side.controller;

import com.example.side.model.entity.Community;
import com.example.side.repository.CommunityRepository;
import com.example.side.service.CommunityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/community")
public class CommunityController {
    private CommunityRepository communityRepository;

    @PostMapping
    public Community createCommunity(@RequestBody Community community) {
        return communityRepository.save(community);
    }

    @GetMapping
    public List<Community> getAllCommunities() {
        return communityRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Community> getCommunityById(@PathVariable Long id) {
        Optional<Community> community = communityRepository.findById(id);
        if (community.isPresent()) {
            return ResponseEntity.ok(community.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Community> updateCommunity(@PathVariable Long id, @RequestBody Community communityDetails) {
        Optional<Community> community = communityRepository.findById(id);
        if (community.isPresent()) {
            Community existingCommunity = community.get();
            existingCommunity.setOccupation(communityDetails.getOccupation());
            return ResponseEntity.ok(communityRepository.save(existingCommunity));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}