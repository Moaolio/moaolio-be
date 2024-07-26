package com.example.side.post.service;

import com.example.side.config.UserDetailsImpl;
import com.example.side.post.Dto.request.PostFilterRequest;
import com.example.side.post.entity.PortfolioPost;
import com.example.side.post.repository.PortfolioPostRepository;
import com.example.side.user.repository.UserRepository;
import com.example.side.post.Dto.request.PortfolioPostRequest;
import com.example.side.post.Dto.response.PortfolioPostResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
        PortfolioPost portfolioPost = (PortfolioPost) portfolioPostRepository.findById(postId)
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
        PortfolioPost portfolioPost = (PortfolioPost) portfolioPostRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Unknown post ID: " + postId));

        if (!portfolioPost.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new IllegalArgumentException("이 페이지에 대한 권한이 없습니다.");
        }

        portfolioPostRepository.deleteById(postId);
        HashMap<String, Long> responseId = new HashMap<>();
        responseId.put("postId", portfolioPost.getId());
        return responseId;
    }
    //필터링검색
    public List<PortfolioPost> filterPosts(PostFilterRequest filterRequest) {
        Specification<PortfolioPost> spec = Specification.where(null);

        if (filterRequest.getTag() != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.join("postTags").get("tag").get("name"), "%" + filterRequest.getTag() + "%"));
        }

        if (filterRequest.getMaxViews() != null) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("viewCount"), filterRequest.getMaxViews()));
        }
        return portfolioPostRepository.findAll(spec);
    }

    // 모든 포트폴리오 게시글 조회
    public List<PortfolioPostResponse> portfolioPosts() {
        List<PortfolioPost> portfolioPosts = portfolioPostRepository.findAll();
        return portfolioPosts.stream()
                .map(PortfolioPostResponse::new)
                .collect(Collectors.toList());
    }
}