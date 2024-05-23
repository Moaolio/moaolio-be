package com.example.side.repository;

import com.example.side.model.entity.JobList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface JobListRepository extends JpaRepository<JobList, Long> {
    Page<JobList> findAll(Pageable pageable);
    Optional<JobList> findById(Long id);
    Optional<JobList> findByName(String name);
}