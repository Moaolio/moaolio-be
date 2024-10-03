package com.example.side.techStack.service;

import com.example.side.techStack.entity.TechMapping;
import com.example.side.techStack.entity.TechStack;
import com.example.side.techStack.repository.TechMappingRepository;
import com.example.side.techStack.repository.TechStackRepository;
import com.example.side.user.entity.User;
import com.example.side.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechStackService {
    private TechStackRepository techStackRepository;
    private TechMappingRepository techMappingRepository;
    private UserRepository userRepository;

    public void addTechStack(Long userId, TechStack tech) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("없는 id입니다."));

        TechMapping techMapping = new TechMapping();
        techMapping.setUser(user);
        techMapping.setTechStack(tech);
        techMappingRepository.save(techMapping);
    }
    public void updateTechStack(Long userId, TechStack tech) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("없는 id입니다."));

        TechMapping techMapping = techMappingRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("사용자의 기술 스택이 없습니다."));

        techMapping.setTechStack(tech);
        techMappingRepository.save(techMapping);
    }
    public void deleteTechStack(Long userId, TechStack tech) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("없는 id입니다."));

        TechMapping techMapping = techMappingRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("분야가 없습니다."));

        techMappingRepository.delete(techMapping);
    }
    // 기술스택 조회
    public TechMapping getTechStack(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("없는 id입니다."));

        return techMappingRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("사용자의 기술 스택이 없습니다."));
    }
}
