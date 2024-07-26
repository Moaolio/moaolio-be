package com.example.side.post.like.repository;

import com.example.side.post.like.entity.PostLike;
import com.example.side.user.entity.User;
import com.example.side.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByUserAndPost(User user, Post post); // 메서드 이름이 엔티티 필드와 일치해야 합니다.
}
