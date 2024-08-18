package com.example.side.techStack.repository;

import com.example.side.techStack.entity.TechStack;
import com.example.side.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechStackRepository extends JpaRepository<TechStack, Long> {
    int countByUser(User user);
    Optional<TechStack> findByUser(User user);
}
