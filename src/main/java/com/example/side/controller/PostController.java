package com.example.side.controller;

import com.example.side.config.UserDetailsImpl;
import com.example.side.request.PostRequest;
import com.example.side.response.PostResponse;
import com.example.side.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/post")

public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    //생성
    @PostMapping("/post")
    public PostResponse createPost(@RequestBody PostRequest postRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.createPost(postRequest, userDetails);
    }
    // 수정
    @PutMapping("/post")
    public PostResponse updatePost(@PathVariable Long postId, @RequestBody PostRequest postRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.updatePost(postId,postRequest, userDetails);
    }
    //삭제
    @DeleteMapping("/post/{postId}")
    public void deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(postId, userDetails);
    }
    //조회
    @GetMapping("/get/{postId}")
    public PostResponse getPost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.getPost(postId, userDetails);
    }
    //내 게시글 조회
    @GetMapping("/mypost")
    public PostResponse<List<MyPostResponse>> getMyPost(@PathVariable String userId) {
        List<MyPostResponse> myPostResponse = postService.getMyPost(userId);
        return PostResponse.ok(myPostResponse);
    }
    //게시글 필터링 조회(tag, category, created)
    @GetMapping("/search")
    public PostResponse<List<PostResponse>> searchPost(@RequestParam String tag, @RequestParam String category, @RequestParam String created) {
        List<PostResponse> postResponse = postService.searchPost(tag, category, created);
        return PostResponse.ok(postResponse);
    }
}