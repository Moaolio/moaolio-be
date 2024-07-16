package com.example.side.service;

import com.example.side.config.UserDetailsImpl;
import com.example.side.model.entity.PortfolioPost;
import com.example.side.repository.PortfolioPostRepository;
import com.example.side.repository.UserRepository;
import com.example.side.request.PortfolioPostRequest;
import com.example.side.response.PortfolioPostResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PortfolioPostService {
    private final PortfolioPostRepository portfolioPostRepository;
    private final UserRepository userRepository;

    public PortfolioPostService(PortfolioPostRepository portfolioPostRepository, UserRepository userRepository) {
        this.portfolioPostRepository = portfolioPostRepository;
        this.userRepository = userRepository;
    }

    // 생성
    @Transactional
    public PortfolioPostResponse createPost(PortfolioPostRequest postRequest, UserDetailsImpl userDetails) {
        PortfolioPost post = new PortfolioPost(postRequest, userDetails.getUser());
        PortfolioPost savedPost = portfolioPostRepository.save(post);
        return new PortfolioPostResponse(savedPost);
    }
    // 수정
    @Transactional
    public PortfolioPostResponse updatePost(Long postId, PortfolioPostRequest postRequest, UserDetailsImpl userDetails) {
        PortfolioPost post = portfolioPostRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Unknown post ID: " + postId));
        post.update(postRequest);
        return new PortfolioPostResponse(post);
    }
    // 삭제
    @Transactional
    public void deletePost(Long postId, UserDetailsImpl userDetails) {
        PortfolioPost post = portfolioPostRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Unknown post ID: " + postId));
        if (!post.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new IllegalArgumentException("You can only delete your own posts");
        }
        portfolioPostRepository.delete(post);
    }

    
}
