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
    /**
     * 1번. 프론트에서 1개의 요청을 보낼때 2가지 컨트롤러가 응답할 수 있다면
     * 응답값 프론트에서 확인
     *
     * 2번. 1개의 요청을 보낼때 1가지 컨트롤러가 응답할 수 있다면
     * 지금 1개의 Response를 더 만든다. 거기에 내가 좋아요 여부를 확인할 수 있는 필드를 추가
     * 만약에 응답값
     */
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
