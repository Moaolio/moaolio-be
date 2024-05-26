package com.example.side.service.Impl;

import com.example.side.model.entity.JobList;
import com.example.side.repository.JobListRepository;
import com.example.side.service.JobListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobListServiceImpl implements JobListService {
    @Autowired
    JobListRepository jobListRepository;

    @Override
    public Iterable<JobList> selectAll() {
        return jobListRepository.findAll();
    }
    @Override
    public Optional<JobList> selectOneById(Long id) {
        return jobListRepository.findById(id);
    }
}
