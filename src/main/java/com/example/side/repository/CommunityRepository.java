package com.example.side.repository;

import com.example.side.model.entity.Community;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    // 직업(occupation)으로 커뮤니티를 페이징 조회하는 메서드
    Page<Community> findByOccupation(String occupation, Pageable pageable);

}