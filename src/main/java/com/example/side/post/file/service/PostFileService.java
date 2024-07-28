package com.example.side.post.file.service;

import com.example.side.post.entity.Post;
import com.example.side.post.file.entity.PostFile;
import com.example.side.post.file.repository.PostFileRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostFileService {
    private final PostFileRepository postFileRepository;

    public PostFileService(PostFileRepository postFileRepository) {
        this.postFileRepository = postFileRepository;
    }

    public List<PostFile> getFilesByPost(Post post) {
        return postFileRepository.findByPost(post);
    }
}
