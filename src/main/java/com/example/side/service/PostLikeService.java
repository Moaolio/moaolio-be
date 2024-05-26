package com.example.side.service;

import com.example.side.model.entity.PostLike;

import java.util.Optional;

public interface PostLikeService {
    Iterable<PostLike> selectAll();
    Optional<PostLike> selectOneById(Long id);
    PostLike insertPostLike(PostLike postLike);
    void updatePostLike(PostLike postLike);
    void deletePostLike(Long id);
}