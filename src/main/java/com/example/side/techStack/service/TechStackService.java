package com.example.side.techStack.service;

import com.example.side.techStack.entity.TechStack;
import com.example.side.techStack.repository.TechStackRepository;
import com.example.side.user.entity.User;
import com.example.side.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechStackService {
    private TechStackRepository techStackRepository;

    private UserRepository userRepository;

    public void addTechStack(Long userId, String tech) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("없는 id입니다."));

        if (techStackRepository.countByUser(user) >= 3) {
            throw new IllegalStateException("분야가 3개 이상입니다.");
        }

        TechStack techStack = new TechStack();
        techStack.setUser(user);
        techStack.setTechName(tech);
        techStackRepository.save(techStack);
    }
    public void updateTechStack(Long userId, String tech) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("없는 id입니다."));

        TechStack techStack = techStackRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("더이상 분야를 정할 수 없습니다."));

        techStack.setTechName(tech);
        techStackRepository.save(techStack);
    }
    public void deleteTechStack(Long userId, String tech) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("없는 id입니다."));

        TechStack techStack = techStackRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("분야가 없습니다."));

        techStackRepository.delete(techStack);
    }
    //
}
