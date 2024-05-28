package com.example.side.service.Impl;

import com.example.side.model.entity.TechStack;
import com.example.side.repository.TechStackRepository;
import com.example.side.service.TechStackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TechStackServiceImpl implements TechStackService {
    @Autowired
    TechStackRepository techStackRepository;
    @Override
    public Iterable<TechStack> selectAll() {
        return techStackRepository.findAll();
    }
    @Override
    public Optional<TechStack> selectOneById(Long id) {
        return techStackRepository.findById(id);
    }
    @Override
    public TechStack insertTechStack(TechStack techStack) {
        return techStackRepository.save(techStack);
    }
    @Override
    public void updateTechStack(TechStack techStack) {
        techStackRepository.save(techStack);
    }

}
