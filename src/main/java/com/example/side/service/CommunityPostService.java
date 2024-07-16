package com.example.side.service;

import com.example.side.config.UserDetailsImpl;
import com.example.side.model.entity.CommunityPost;
import com.example.side.repository.CommunityPostRepository;
import com.example.side.repository.UserRepository;
import com.example.side.request.CommunityPostRequest;
import com.example.side.response.CommunityPostResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CommunityPostService {
    private final UserRepository userRepository;
    private final CommunityPostRepository communityPostRepository;

    public CommunityPostService(UserRepository userRepository, CommunityPostRepository communityPostRepository) {
        this.userRepository = userRepository;
        this.communityPostRepository = communityPostRepository;
    }

    @Transactional //생성
    public CommunityPostResponse createPost(CommunityPostRequest postRequest, UserDetailsImpl userDetails) {
        CommunityPost post = new CommunityPost(postRequest, userDetails.getUser());
        CommunityPost savedPost = communityPostRepository.save(post);
        return new CommunityPostResponse(savedPost);
    }
    @Transactional //수정
    public CommunityPostResponse updatePost(Long postId, CommunityPostRequest postRequest, UserDetailsImpl userDetails) {
        CommunityPost post = communityPostRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Unknown post ID: " + postId));
        post.update(postRequest);
        return new CommunityPostResponse(post);
    }
    @Transactional //삭제
    public void deletePost(Long postId, UserDetailsImpl userDetails) {
        CommunityPost post = communityPostRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Unknown post ID: " + postId));
        if (!post.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new IllegalArgumentException("You can only delete your own posts");
        }
        communityPostRepository.delete(post);
    }

}
