package com.example.side.controller;

import com.example.side.config.UserDetailsImpl;
import com.example.side.request.PostRequest;
import com.example.side.response.PostResponse;
import com.example.side.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 생성
    @PostMapping("/post/create")
    public PostResponse createPost(@RequestBody PostRequest postRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.createPost(postRequest, userDetails);
    }

    // 게시글 수정
    @PutMapping("/post/{postId}")
    public PostResponse updatePost(@PathVariable Long postId, @RequestBody PostRequest postRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.updatePost(postId, postRequest, userDetails);
    }

    // 게시글 삭제
    @DeleteMapping("/post/{postId}")
    public HashMap<String, Long> deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deletePost(postId, userDetails);
    }

    // 포트폴리오 게시글 조회 (최신순)
    @GetMapping("/posts/portfolio")
    public List<PostResponse> recentPosts() {
        return postService.portfolioPosts();
    }
    @GetMapping("/posts/commutity"){
        public List<PostResponse>
    }

    // 내 게시글 전체 조회
    @GetMapping("/posts/{userId}")
    public List<PostResponse> myPosts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.myPosts(userDetails);
    }

    // 게시글 상세 조회
    @GetMapping("/posts/{postId}")
    public PostResponse getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }
}
