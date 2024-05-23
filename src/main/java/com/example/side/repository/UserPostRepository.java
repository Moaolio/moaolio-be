package com.example.side.repository;

import com.example.side.model.entity.UserPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPostRepository extends JpaRepository<UserPost, Long> {
    // 유저 아이디로 유저 포스트를 페이징 조회하는 메서드
    Page<UserPost> findByUserId(Long userId, Pageable pageable);
}