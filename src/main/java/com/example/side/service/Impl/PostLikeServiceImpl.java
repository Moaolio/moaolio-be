package com.example.side.service.Impl;

import com.example.side.model.entity.PostLike;
import com.example.side.repository.PostLikeRepository;
import com.example.side.service.PostLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostLikeServiceImpl implements PostLikeService {
    @Autowired
    PostLikeRepository postLikeRepository;

    public Iterable<PostLike> selectAll() {
        return postLikeRepository.findAll();
    }

    @Override
    public Optional<PostLike> selectOneById(Long id) {
        return postLikeRepository.findById(id);
    }

    @Override
    public PostLike insertPostLike(PostLike postLike) {
        return postLikeRepository.save(postLike);
    }

    @Override
    public void deletePostLike(Long id) {
        postLikeRepository.deleteById(id);
    }

    @Override
    public void updatePostLike(PostLike postLike) {
        postLikeRepository.save(postLike);
    }
}
