package com.example.side.post.repository;

import com.example.side.post.entity.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {

    CommunityPost save(CommunityPost communityPost);
}
