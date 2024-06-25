package com.example.side.repository;

import com.example.side.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // 유저 아이디로 유저 포스트를 페이징 조회하는 메서드
    List<Post> findByTagAndCategoryAndCreatedAt(String tag, String category, LocalDateTime createdAt);

    List<Post> findByUserId(Long userId);

//    List<UserPost> search(String tag, String category, LocalDateTime createdAt);

}