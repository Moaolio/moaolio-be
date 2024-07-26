package com.example.side.post.service;

import com.example.side.auth.CustomUserDetails;
import com.example.side.common.exception.PostNotFoundException;
import com.example.side.post.entity.CommunityPost;
import com.example.side.post.dto.request.CommunityPostRequest;
import com.example.side.post.dto.response.CommunityPostResponse;
import com.example.side.post.repository.PostRepository;
import com.example.side.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommunityPostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional //생성
    public CommunityPostResponse createPost(CommunityPostRequest communityPostRequest, CustomUserDetails userDetails) {
//        CommunityPost post = new CommunityPost(postRequest, userDetails.getUser());

        CommunityPost post = CommunityPost.builder()
                .title(communityPostRequest.getTitle())
                .content(communityPostRequest.getContent())
                .user(userDetails.getUser())
                .build();

        CommunityPost savedPost = postRepository.save(post);
        return new CommunityPostResponse(savedPost.getId(), savedPost.getTitle(), savedPost.getContent());
    }

    @Transactional //수정
    public CommunityPostResponse updatePost(Long postId, CommunityPostRequest postRequest, CustomUserDetails userDetails) {
        CommunityPost post = (CommunityPost) postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException());

        post.updatePost(postRequest.getTitle(), postRequest.getContent());

        return new CommunityPostResponse(post.getId(), post.getTitle(), post.getContent());
    }

    @Transactional //삭제
    public void deletePost(Long postId, CustomUserDetails userDetails) {
        CommunityPost post = (CommunityPost) postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException());
        if (!post.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new IllegalStateException("You can only delete your own posts");
        }
        postRepository.delete(post);
    }

}
