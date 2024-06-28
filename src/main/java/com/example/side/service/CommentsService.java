package com.example.side.service;

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

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentsResponse createComments(CommentsRequest commentsRequest) {
        Post findPost = postRepository.findById(commentsRequest.getUserPostId()).get();
        User findUser = userRepository.findById(commentsRequest.getUserId()).get();

        Comments comments = new Comments();
        comments.setDescription(commentsRequest.getDescription());
        comments.setCreatedAt(LocalDateTime.now());
        comments.setUpdatedAt(LocalDateTime.now());
        comments.setPost(findPost);
        comments.setUser(findUser);

        commentsRepository.save(comments);

        CommentsResponse commentsResponse = new CommentsResponse().builder()
                .id(comments.getId())
                .description(comments.getDescription())
                .createdAt(comments.getCreatedAt())
                .updatedAt(comments.getUpdatedAt())
                .postId(comments.getPost().getId())
                .userId(comments.getUser().getId())
                .build();
        return commentsResponse;
    }

    //수정
    public CommentsResponse updateComments(Long commentsId, CommentsRequest commentsRequest, UserDetailsImpl userDetails) {
        Comments comments = commentsRepository.findById(commentsId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
        if(!comments.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        comments.setDescription(commentsRequest.getDescription());
        comments.setUpdatedAt(LocalDateTime.now());
        //저장
        Comments saveComments = commentsRepository.save(comments);
        CommentsResponse commentsResponse = new CommentsResponse(saveComments);
        return commentsResponse;
    }

    //삭제
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
}