package com.example.side.service.impl;

import com.example.side.model.entity.UserPost;
import com.example.side.repository.UserPostRepository;
import com.example.side.request.UserPostRequest;
import com.example.side.response.UserPostResponse;
import com.example.side.service.UserPostService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class UserPostServiceImpl implements UserPostService {

    private final UserPostRepository userPostRepository;

    public UserPostServiceImpl(UserPostRepository userPostRepository) {
        this.userPostRepository = userPostRepository;
    }



    public Iterable<UserPost> selectAll() {
        return userPostRepository.findAll();
    }

    @Override
    public Optional<UserPost> selectOneById(Long id) {
        return userPostRepository.findById(id);
    }

    @Override
    public UserPostResponse createPost(UserPostRequest userPostRequest) {
        UserPost userPost = new UserPost(userPostRequest);
        userPostRepository.save(userPost);
        return new UserPostResponse(userPost);
    }


    @Transactional
    public Long updatePost(Long id, UserPostRequest userPostRequest) {
        UserPost userPost = userPostRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다."));
        userPost.update(userPostRequest);
        return userPost.getId();
    }

    @Transactional
    public Long deletePost(Long id) {
        userPostRepository.deleteById(id);
        return id;
    }

//    public List<UserPost> searchPosts(String tag, String category, LocalDateTime createdAt) {
//        return userPostRepository.findByTagAndCategoryAndCreatedAt(tag, category, createdAt);
//    }


    public List<UserPost> selectByUserId(Long userId) {
        // UserPostRepository에 findByUserId 메소드가 있다고 가정합니다.
        return userPostRepository.findByUserId(userId);
    }

}