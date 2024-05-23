package com.example.side.repository;

import com.example.side.model.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<Job> findByUserId(Long userId);
}