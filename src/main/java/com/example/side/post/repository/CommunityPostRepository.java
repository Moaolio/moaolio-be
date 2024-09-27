package com.example.side.post.repository;

import com.example.side.post.entity.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {
    //문제없음
    //제목검색
    @Query("SELECT p FROM Post p WHERE p.postType = 'CommunityPost' AND (:title IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%')))")    List<CommunityPost> findPostsByTitle(@Param("title") String title);
    //문제없음
    //카테고리 검색
    List<CommunityPost> findByCategoryId(Long categoryId);


}