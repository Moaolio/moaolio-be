package com.example.side.post.service;

import com.example.side.config.UserDetailsImpl;
import com.example.side.post.entity.CommunityPost;
import com.example.side.post.repository.CommunityPostRepository;
import com.example.side.user.repository.UserRepository;
import com.example.side.post.Dto.request.CommunityPostRequest;
import com.example.side.post.Dto.response.CommunityPostResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CommunityPostService {
    private final UserRepository userRepository;
    private final CommunityPostRepository communityPostRepository;

    public CommunityPostService(UserRepository userRepository, CommunityPostRepository communityPostRepository) {
        this.userRepository = userRepository;
        this.communityPostRepository = communityPostRepository;
    }

    @Transactional //생성
    public CommunityPostResponse createPost(CommunityPostRequest communityPostRequest, UserDetailsImpl userDetails) {
        CommunityPost communityPost = new CommunityPost(communityPostRequest, userDetails.getUser());
        CommunityPost savedCommunityPost = communityPostRepository.save(communityPost);
        return new CommunityPostResponse(savedCommunityPost);
    }
    @Transactional //수정
    public CommunityPostResponse updatePost(Long postId, CommunityPostRequest postRequest, UserDetailsImpl userDetails) {
        CommunityPost communityPost = communityPostRepository.findById(postId).orElseThrow(()
                -> new IllegalArgumentException("Unknown post ID: " + postId));
        if (!communityPost.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new IllegalArgumentException("이 페이지에 대한 권한이 없습니다.");
        }
        communityPost.update(postRequest);
        CommunityPost updatedCommunityPost = communityPostRepository.save(communityPost);
        return new CommunityPostResponse(updatedCommunityPost);
    }

    @Transactional //삭제
    public HashMap<String, Long> deletePost(Long postId, UserDetailsImpl userDetails) {
        CommunityPost post = communityPostRepository.findById(postId).orElseThrow(()
                -> new IllegalArgumentException("Unknown post ID: " + postId));
        if (!post.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new IllegalArgumentException("You can only delete your own posts");
        }
        communityPostRepository.deleteById(postId);
        HashMap<String,Long> responseId = new HashMap<>();
        responseId.put("postId", post.getId());
        return responseId;
    }
    //필터링검색

}
