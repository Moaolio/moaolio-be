package com.example.side.repository;

import com.example.side.model.entity.PostLike;
import com.example.side.model.entity.User;
import com.example.side.model.entity.UserPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByUserAndUserPost(User user, UserPost userPost);
}
