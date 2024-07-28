package com.example.side.post.like.service;

import com.example.side.post.entity.Post;
import com.example.side.post.like.entity.PostLike;
import com.example.side.post.like.repository.PostLikeRepository;
import com.example.side.user.entity.User;

import java.util.Optional;

public class PostLikeService {
    private final PostLikeRepository postLikeRepository;

    public PostLikeService(PostLikeRepository postLikeRepository) {
        this.postLikeRepository = postLikeRepository;
    }

    public Optional<PostLike> getPostLike(User user, Post post) {
        return postLikeRepository.findByUserAndPost(user, post);
    }
}