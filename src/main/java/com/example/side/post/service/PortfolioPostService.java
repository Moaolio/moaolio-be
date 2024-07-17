package com.example.side.post.service;

import com.example.side.auth.CustomUserDetails;
import com.example.side.post.entity.PortfolioPost;
import com.example.side.post.dto.request.PortfolioPostRequest;
import com.example.side.post.dto.response.PortfolioPostResponse;
import com.example.side.post.repository.PostRepository;
import com.example.side.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PortfolioPostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 생성
    @Transactional
    public PortfolioPostResponse createPost(PortfolioPostRequest postRequest, CustomUserDetails userDetails) {

        PortfolioPost post = PortfolioPost.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .user(userDetails.getUser())
                .build();

        PortfolioPost savedPost = postRepository.save(post);
        return new PortfolioPostResponse(savedPost.getId(), savedPost.getTitle(), savedPost.getContent());
    }
    // 수정
    @Transactional
    public PortfolioPostResponse updatePost(Long postId, PortfolioPostRequest postRequest, CustomUserDetails userDetails) {
        PortfolioPost post = (PortfolioPost) postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Unknown post ID: " + postId));

        post.updatePost(postRequest.getTitle(), postRequest.getContent());

        return new PortfolioPostResponse(post.getId(), post.getTitle(), post.getContent());
    }
    // 삭제
    @Transactional
    public void deletePost(Long postId, CustomUserDetails userDetails) {
        PortfolioPost post = (PortfolioPost) postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Unknown post ID: " + postId));
        if (!post.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new IllegalArgumentException("You can only delete your own posts");
        }
        postRepository.delete(post);
    }

    
}
