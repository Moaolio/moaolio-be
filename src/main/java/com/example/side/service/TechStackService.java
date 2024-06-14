package com.example.side.service;

import com.example.side.model.entity.TechStack;

import java.util.Optional;

public interface TechStackService {
    Iterable<TechStack> selectAll();
    Optional<TechStack> selectOneById(Long id);
    TechStack insertTechStack(TechStack techStack);
    void updateTechStack(TechStack techStack);
}