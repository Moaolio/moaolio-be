package com.example.side.repository;

import com.example.side.model.entity.TechStackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface TechStackListRepository extends JpaRepository<TechStackList, Long> {
    Optional<TechStackList> findByName(String name);
}