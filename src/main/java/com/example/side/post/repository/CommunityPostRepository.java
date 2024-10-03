package com.example.side.post.repository;

import com.example.side.post.entity.CommunityPost;
import com.example.side.post.entity.PortfolioPost;
import com.example.side.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Page<CommunityPost> findByUser(User user, Pageable pageable);



}