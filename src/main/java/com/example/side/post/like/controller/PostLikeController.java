package com.example.side.post.like.controller;

import com.example.side.Dto.GlobalResDto;
import com.example.side.config.UserDetailsImpl;
import com.example.side.post.like.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post/like")
public class PostLikeController {
    private final PostLikeService postLikeService;

    @PostMapping("/on/{postId}")
    public GlobalResDto<?> createPostLike(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId) {
        return postLikeService.createPostLike(userDetails, postId);
    }
    @DeleteMapping("/off/{postId}")
    public GlobalResDto<?> deletePostLike(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId) {
        return postLikeService.deletePostLike(userDetails, postId);
    }
}
