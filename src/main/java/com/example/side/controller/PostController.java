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

    // 생성
    @PostMapping
    public PostResponse createPost(@RequestBody PostRequest postRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.createPost(postRequest, userDetails);
    }

    // 수정
    @PutMapping("/{postId}")
    public PostResponse updatePost(@PathVariable Long postId, @RequestBody PostRequest postRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.updatePost(postId, postRequest, userDetails);
    }

    // 삭제
    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(postId, userDetails);
    }

    // 상세조회
    @GetMapping("/{postId}")
    public PostResponse getPost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.getPost(postId, userDetails);
    }

    // 내 게시글 조회
    @GetMapping("/myposts")
    public PostResponse<List<MyPostResponse>> getMyPosts(@RequestParam String userId) {
        List<MyPostResponse> myPostResponse = postService.getMyPosts(userId);
        return PostResponse.ok(myPostResponse);
    }

    // 게시글 필터링 조회 (tag, category, created)
    @GetMapping("/search")
    public PostResponse<List<PostResponse>> searchPosts(@RequestParam(required = false) String tag,
                                                        @RequestParam(required = false) String category,
                                                        @RequestParam(required = false) String created) {
        List<PostResponse> postResponse = postService.searchPosts(tag, category, created);
        return PostResponse.ok(postResponse);
    }
}
