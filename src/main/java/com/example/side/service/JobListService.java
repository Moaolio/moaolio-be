package com.example.side.service;

import com.example.side.model.entity.JobList;

import java.util.Optional;

public interface JobListService {
    Iterable<JobList> selectAll();
    Optional<JobList> selectOneById(Long id);
}