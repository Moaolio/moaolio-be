package com.example.side.service.Impl;

import com.example.side.model.entity.Job;
import com.example.side.repository.JobRepository;
import com.example.side.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    JobRepository jobRepository;

    @Override
    public Iterable<Job> selectAll() {
        return jobRepository.findAll();
    }
    @Override
    public Optional<Job> selectOneById(Long id) {
        return jobRepository.findById(id);
    }
}
