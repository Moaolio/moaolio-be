package com.example.side.comments.controller;

import com.example.side.Dto.GlobalResDto;
import com.example.side.auth.CustomUserDetails;
import com.example.side.comments.dto.request.CommentsRequest;
import com.example.side.comments.dto.response.CommentsResponse;
import com.example.side.comments.service.CommentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentsController {

    private final CommentsService commentsService;

    // 생성
    @PostMapping("/post")
    public GlobalResDto<Object> createComments(@RequestBody CommentsRequest commentsRequest, @AuthenticationPrincipal CustomUserDetails userDetails) {
        CommentsResponse commentsResponse = commentsService.createComments(commentsRequest, userDetails);
        return GlobalResDto.success(commentsResponse, "댓글 달기 성공");
    }

    // 수정
    @PutMapping("/update/{commentsId}")
    public GlobalResDto<Object> updateComments(@PathVariable Long commentsId, @RequestBody CommentsRequest commentsRequest, @AuthenticationPrincipal CustomUserDetails userDetails) {
        CommentsResponse commentsResponse = commentsService.updateComments(commentsId, commentsRequest, userDetails);
        return GlobalResDto.success(commentsResponse, "댓글 수정 성공");
    }

    // 삭제
    @DeleteMapping("/delete/{commentsId}")
    public GlobalResDto<Object> deleteComments(@PathVariable Long commentsId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        commentsService.deleteComments(commentsId, userDetails);
        return GlobalResDto.success(null, "댓글 삭제 성공");
    }

    // 조회
    @GetMapping("/post/{postId}")
    public GlobalResDto<Object> getComments(@PathVariable Long postId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<CommentsResponse> commentsByPostId = commentsService.findCommentsByPostId(postId, userDetails);
        return GlobalResDto.success(commentsByPostId, "게시글 댓글 조회 성공");
    }
}
