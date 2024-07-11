package com.example.side.service;

import com.example.side.common.exception.CommentNotFoundException;
import com.example.side.common.exception.PostNotFoundException;
import com.example.side.common.exception.UserNotFoundException;
import com.example.side.config.UserDetailsImpl;
import com.example.side.model.entity.Comments;
import com.example.side.model.entity.Post;
import com.example.side.model.entity.User;
import com.example.side.repository.CommentsRepository;
import com.example.side.repository.PostRepository;
import com.example.side.repository.UserRepository;
import com.example.side.request.CommentsRequest;
import com.example.side.request.PostRequest;
import com.example.side.response.CommentsResponse;
import com.example.side.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 생성
    @Transactional
    public CommentsResponse createComments(CommentsRequest commentsRequest, UserDetailsImpl userDetails) {
        Post findPost = postRepository.findById(commentsRequest.getUserPostId()).orElseThrow(() -> new PostNotFoundException("게시글을 찾을 수 없습니다."));
        User findUser = userRepository.findById(commentsRequest.getUserId()).orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));

        Comments comments = new Comments(commentsRequest.getDescription(), findPost, findUser);
        commentsRepository.save(comments);

        CommentsResponse commentsResponse = CommentsResponse.builder()
                .id(comments.getId())
                .description(comments.getDescription())
                .userId(comments.getUser().getId())
                .postId(comments.getPost().getId())
                .build();
        return commentsResponse;
    }

    //수정
    @Transactional
    public CommentsResponse updateComments(Long commentsId, CommentsRequest commentsRequest, UserDetailsImpl userDetails) {
        Comments comments = commentsRepository.findById(commentsId).orElseThrow(() -> new CommentNotFoundException("존재하지 않는 댓글입니다."));
        if(!comments.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        comments.setDescription(commentsRequest.getDescription());

        CommentsResponse commentsResponse = new CommentsResponse(comments);
        return commentsResponse;
    }

    //삭제
    @Transactional
    public HashMap<String,Long> deleteComments(Long commentsId, UserDetailsImpl userDetails) {
        Comments comments = commentsRepository.findById(commentsId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        if(!comments.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }

        HashMap<String,Long> responseId = new HashMap<>();
        responseId.put("CommentsId",comments.getId());
        commentsRepository.deleteById(commentsId);
        return responseId;
    }

    // 특정 글의 댓글 조회
    public List<CommentsResponse> findCommentsByPostId(Long postId, UserDetailsImpl userDetails) {
        List<Comments> findComments = commentsRepository.findByPostId(postId);
        List<CommentsResponse> returnCommentsResponse = new ArrayList<>();

        findComments.stream()
                .map(comments -> new CommentsResponse(comments))
                .forEach(returnCommentsResponse::add);

        return returnCommentsResponse;
    }

    // 내 댓글 모두 조회
    public List<CommentsResponse> findCommentsByUserId(Long userId, UserDetailsImpl userDetails) {
        List<Comments> findComments = commentsRepository.findByUserId(userDetails.getUser().getId());
        List<CommentsResponse> returnCommentsResponse = new ArrayList<>();
        findComments.stream()
                .map(comments -> new CommentsResponse(comments))
                .forEach(returnCommentsResponse::add);
        return returnCommentsResponse;
    }

}