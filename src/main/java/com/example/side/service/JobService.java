package com.example.side.service;

import com.example.side.model.entity.Job;

import java.util.Optional;

public interface JobService {
    Iterable<Job> selectAll();
    Optional<Job> selectOneById(Long id);
}