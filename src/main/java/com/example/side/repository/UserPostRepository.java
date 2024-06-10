package com.example.side.repository;

import com.example.side.model.entity.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserPostRepository extends JpaRepository<UserPost, Long> {
    // 유저 아이디로 유저 포스트를 페이징 조회하는 메서드
    List<UserPost> findByTagAndCategoryAndCreatedAt(String tag, String category, LocalDateTime createdAt);

    List<UserPost> findByUserId(Long userId);

//    List<UserPost> search(String tag, String category, LocalDateTime createdAt);

}