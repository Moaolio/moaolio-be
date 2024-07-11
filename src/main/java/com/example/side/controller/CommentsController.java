package com.example.side.controller;

import com.example.side.config.UserDetailsImpl;
import com.example.side.request.CommentsRequest;
import com.example.side.response.CommentsResponse;
import com.example.side.service.CommentsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
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
    public CommentsResponse createComments(@RequestBody CommentsRequest commentsRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentsService.createComments(commentsRequest, userDetails);
    }

    // 수정
    @PutMapping("/update/{commentsId}")
    public CommentsResponse updateComments(@PathVariable Long commentsId, @RequestBody CommentsRequest commentsRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentsService.updateComments(commentsId, commentsRequest, userDetails);
    }

    // 삭제
    @DeleteMapping("/delete/{commentsId}")
    public void deleteComments(@PathVariable Long commentsId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentsService.deleteComments(commentsId, userDetails);
    }

    // 조회
    @GetMapping("/post/{postId}")
    public List<CommentsResponse> getComments(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentsService.findCommentsByPostId(postId, userDetails);
    }
}
