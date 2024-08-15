package com.example.side.post.repository;

import com.example.side.post.entity.PortfolioPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PortfolioPostRepository extends JpaRepository<PortfolioPost, Long> {
    @Query("SELECT p FROM Post p WHERE (:tags IS NULL OR p.tag IN :tags) " +
            "AND (:startDate IS NULL OR p.createdAt >= :startDate) " +
            "AND (:endDate IS NULL OR p.createdAt <= :endDate) " +
            "ORDER BY CASE WHEN :sortBy = 'likes' THEN p.likeCount END DESC, " +
            "CASE WHEN :sortBy = 'createdDate' THEN p.createdAt END DESC")
    List<PortfolioPost> findPosts(@Param("tags") List<String> tags,
                         @Param("startDate") LocalDateTime startDate,
                         @Param("endDate") LocalDateTime endDate,
                         @Param("sortBy") String sortBy);
    //타이틀검색
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:title%")
    List<PortfolioPost> findByTitleContaining(@Param("title") String title);
}

