package com.example.side.post.service;

import com.example.side.config.UserDetailsImpl;
import com.example.side.post.entity.PortfolioPost;
import com.example.side.post.repository.PortfolioPostRepository;
import com.example.side.user.repository.UserRepository;
import com.example.side.post.dto.request.PortfolioPostRequest;
import com.example.side.post.dto.response.PortfolioPostResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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
    public PortfolioPostResponse createPost(PortfolioPostRequest portfolioPostRequest, UserDetailsImpl userDetails) {
        PortfolioPost portfolioPost = new PortfolioPost(portfolioPostRequest, userDetails.getUser());
        PortfolioPost savedPortfolioPost = portfolioPostRepository.save(portfolioPost);
        return new PortfolioPostResponse(savedPortfolioPost);
    }

    // 수정
    @Transactional
    public PortfolioPostResponse updatePost(Long postId, PortfolioPostRequest postRequest, UserDetailsImpl userDetails) {
        PortfolioPost portfolioPost = portfolioPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Unknown post ID: " + postId));

        if (!portfolioPost.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new IllegalArgumentException("이 페이지에 대한 권한이 없습니다.");
        }

        portfolioPost.update(postRequest);
        PortfolioPost updatedPortfolioPost = portfolioPostRepository.save(portfolioPost);
        return new PortfolioPostResponse(updatedPortfolioPost);
    }

    // 삭제
    @Transactional
    public HashMap<String, Long> deletePost(Long postId, UserDetailsImpl userDetails) {
        PortfolioPost portfolioPost = portfolioPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Unknown post ID: " + postId));

        if (!portfolioPost.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new IllegalArgumentException("이 페이지에 대한 권한이 없습니다.");
        }

        portfolioPostRepository.deleteById(postId);
        HashMap<String, Long> responseId = new HashMap<>();
        responseId.put("postId", portfolioPost.getId());
        return responseId;
    }
}