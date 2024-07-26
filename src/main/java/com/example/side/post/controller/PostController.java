package com.example.side.post.controller;

import com.example.side.config.UserDetailsImpl;
import com.example.side.post.Dto.request.CommunityPostRequest;
import com.example.side.post.Dto.request.PortfolioPostRequest;
import com.example.side.post.Dto.request.PostFilterRequest;
import com.example.side.post.Dto.response.CommunityPostResponse;
import com.example.side.post.Dto.response.PortfolioPostResponse;
import com.example.side.post.entity.Post;
import com.example.side.post.service.CommunityPostService;
import com.example.side.post.service.PortfolioPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/post")
public class PostController {
    private final PortfolioPostService portfolioPostService;
    private final CommunityPostService communityPostService;


    public PostController(PortfolioPostService portfolioPostService, CommunityPostService communityPostService) {
        this.portfolioPostService = portfolioPostService;
        this.communityPostService = communityPostService;
    }

    // 포트폴리오 게시글 생성
    @PostMapping("/create/portfolio")
    public PortfolioPostResponse createPost(@RequestBody PortfolioPostRequest portfolioPostRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return portfolioPostService.createPost(portfolioPostRequest, userDetails);
    }

    // 커뮤니티 게시글 생성
    @PostMapping("/create/community")
    public CommunityPostResponse createPost(@RequestBody CommunityPostRequest communityPostRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return communityPostService.createPost(communityPostRequest, userDetails);
    }


    // 포트폴리오 게시글 수정
    @PutMapping("/update/portfolio/{postId}")
    public PortfolioPostResponse updatePost(@PathVariable Long postId, @RequestBody PortfolioPostRequest portfolioPostRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return portfolioPostService.updatePost(postId, portfolioPostRequest, userDetails);
    }

    // 커뮤니티 게시글 수정
    @PutMapping("/update/community/{postId}")
    public CommunityPostResponse updatePost(@PathVariable Long postId, @RequestBody CommunityPostRequest communityPostRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return communityPostService.updatePost(postId, communityPostRequest, userDetails);
    }


    // 포트폴리오 게시글 삭제
    @DeleteMapping("/delete/portfolio/{postId}")
    public HashMap<String, Long> deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return portfolioPostService.deletePost(postId, userDetails);
    }

    // 커뮤니티 게시글 삭제 - Method name changed to differentiate
    @DeleteMapping("/delete/community/{postId}")
    public HashMap<String, Long> deleteCommunityPost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return communityPostService.deletePost(postId, userDetails);
    }




    // 포트폴리오 게시글 조회 (최신순)
    @GetMapping("/get/portfolio")
    public List<PortfolioPostResponse> recentPortfolioPosts() {
        return portfolioPostService.portfolioPosts();
    }



    // 커뮤니티 게시글 조회 (최신순)
    @GetMapping("/get/community")
    public List<CommunityPostResponse> recentCommunityPosts() {
        return communityPostService.communityPosts();
    }

    @PostMapping("/get/filter")
    public List<Post> filterPosts(@RequestBody PostFilterRequest filterRequest) {
        return portfolioPostService.filterPosts(filterRequest);
    }
}
