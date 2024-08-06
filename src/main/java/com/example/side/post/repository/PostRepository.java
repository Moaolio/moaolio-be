package com.example.side.post.repository;

import com.example.side.post.entity.PortfolioPost;
import com.example.side.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
