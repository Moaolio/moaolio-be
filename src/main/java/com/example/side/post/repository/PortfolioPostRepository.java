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
//    @Query("SELECT p FROM PortfolioPost p WHERE (:tags IS NULL OR p.postTags IN :tags) " +
//            "AND (:startDate IS NULL OR p.createdAt >= :startDate) " +
//            "AND (:endDate IS NULL OR p.createdAt <= :endDate) " +
//            "ORDER BY CASE WHEN :sortBy = 'likes' THEN p.likeCount " +
//            "WHEN :sortBy = 'createdDate' THEN p.createdAt END DESC")
//    List<PortfolioPost> findByPosts(@Param("tags") List<String> tags,
//                                    @Param("startDate") LocalDateTime startDate,
//                                    @Param("endDate") LocalDateTime endDate,
//                                    @Param("sortBy") String sortBy);

//    @Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%'))")
//    List<PortfolioPost> findByTitle(@Param("title") String title);
}
