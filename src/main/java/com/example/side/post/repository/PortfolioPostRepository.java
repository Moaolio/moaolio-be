package com.example.side.post.repository;

import com.example.side.post.entity.PortfolioPost;
import com.example.side.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioPostRepository extends JpaRepository<PortfolioPost, Long>, JpaSpecificationExecutor<PortfolioPost> {

}

