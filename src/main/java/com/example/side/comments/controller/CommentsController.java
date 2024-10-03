package com.example.side.comments.controller;

import com.example.side.Dto.GlobalResDto;
import com.example.side.auth.CustomUserDetails;
import com.example.side.comments.dto.request.CommentsRequest;
import com.example.side.comments.dto.response.CommentsResponse;
import com.example.side.comments.repository.CommentsRepository;
import com.example.side.comments.service.CommentsService;
import com.example.side.post.repository.CommunityPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentsController {

    private final CommentsService commentsService;
    private final CommunityPostRepository communityPostRepository;
    private final CommentsRepository commentsRepository;

    // 생성
    @PostMapping("/comment/post")
    public GlobalResDto<Object> createComments(@RequestBody CommentsRequest commentsRequest, @AuthenticationPrincipal CustomUserDetails userDetails) {
        CommentsResponse commentsResponse = commentsService.createComments(commentsRequest, userDetails);
        return GlobalResDto.success(commentsResponse, "댓글 달기 성공");
    }

    // 수정
    @PutMapping("/comment/update/{commentsId}")
    public GlobalResDto<Object> updateComments(@PathVariable Long commentsId, @RequestBody CommentsRequest commentsRequest, @AuthenticationPrincipal CustomUserDetails userDetails) {

        CommentsResponse findCommentsResponse = commentsService.findById(commentsId);
        if(!findCommentsResponse.getUserId().equals(userDetails.getUser().getId())) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        CommentsResponse commentsResponse = commentsService.updateComments(commentsId, commentsRequest, userDetails);
        return GlobalResDto.success(commentsResponse, "댓글 수정 성공");
    }

    // 삭제
    @DeleteMapping("/comment/delete/{commentsId}")
    public GlobalResDto<Object> deleteComments(@PathVariable Long commentsId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        //권한확인
        CommentsResponse findCommentsResponse = commentsService.findById(commentsId);
        if(!findCommentsResponse.getUserId().equals(userDetails.getUser().getId())) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        commentsService.deleteComments(commentsId, userDetails);
        return GlobalResDto.success(null, "댓글 삭제 성공");
    }

    // 게시글에 대한 댓글 조회
    @GetMapping("/comment/post/{postId}")
    public GlobalResDto<Object> getComments(@PathVariable Long postId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        List<CommentsResponse> commentsByPostId = commentsService.findCommentsByPostId(postId, userDetails);
        return GlobalResDto.success(commentsByPostId, "게시글 댓글 조회 성공");
    }

    // 내 댓글 모두 조회
    @GetMapping("/comment/my")
    public GlobalResDto<List<CommentsResponse>> getMyComments(@AuthenticationPrincipal CustomUserDetails userDetails) {
        List<CommentsResponse> commentsResponse = commentsService.findCommentsByUserId(userDetails);
        return GlobalResDto.success(commentsResponse, "내가 작성한 댓글 조회 성공");
    }

}
