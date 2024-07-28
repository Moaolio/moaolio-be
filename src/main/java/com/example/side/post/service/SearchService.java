package com.example.side.post.service;

import com.example.side.post.repository.PostRepository;

public class SearchService {
    private final PostRepository postRepository;

    public SearchService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
