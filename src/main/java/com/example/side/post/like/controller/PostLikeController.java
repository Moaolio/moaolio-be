package com.example.side.post.like.controller;

import com.example.side.Dto.GlobalResDto;
import com.example.side.auth.CustomUserDetails;
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
    public GlobalResDto<String> createPostLike(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long postId) {
        try {
            postLikeService.createPostLike(userDetails, postId);
            return GlobalResDto.success("Post liked successfully", "Like added");
        } catch (Exception e) {
            return GlobalResDto.fail(500, null, "Failed to like the post");
        }
    }

    @DeleteMapping("/off/{postId}")
    public GlobalResDto<String> deletePostLike(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable Long postId) {
        try {
            postLikeService.deletePostLike(userDetails, postId);
            return GlobalResDto.success("Post unliked successfully", "Like removed");
        } catch (Exception e) {
            return GlobalResDto.fail(500, null, "Failed to unlike the post");
        }
    }
}
