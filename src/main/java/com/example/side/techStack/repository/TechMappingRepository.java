package com.example.side.techStack.repository;

import com.example.side.techStack.entity.TechMapping;
import com.example.side.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TechMappingRepository extends JpaRepository<TechMapping, Long> {
    Long countByUser(User user);
    Optional<TechMapping> findByUser(User user);

}
