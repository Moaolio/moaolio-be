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

    //좋아요 순 정렬
    @Query("SELECT p FROM Post p WHERE p.postType = 'PortfolioPost' ORDER BY p.likeCount DESC")
    List<PortfolioPost> findAllByOrderByLikeCountDesc();


    //검색
    @Query("SELECT p FROM Post p WHERE p.postType = 'PortfolioPost' AND LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<PortfolioPost> findByTitle(@Param("title") String title);
}
