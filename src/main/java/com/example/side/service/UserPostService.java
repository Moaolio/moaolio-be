package com.example.side.service;

import com.example.side.model.entity.UserPost;
import com.example.side.request.UserPostRequest;
import com.example.side.response.UserPostResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserPostService {
//    1. 전체 게시글 조회
    Iterable<UserPost> selectAll();
//    2. 자신 게시글 조회
    Optional<UserPost> selectOneById(Long id);
//    3. 게시글 작성
    UserPostResponse createPost(UserPostRequest userPostRequest);
//    4. 게시글 수정

    Long updatePost(Long id, UserPostRequest userPostRequest);

    //    5. 게시글 삭제
    Long deletePost(Long id);
//    6. 게시글 필터링
//    @Query("SELECT u FROM UserPost u WHERE u.category = :category AND u.tag = :tag AND u.createdAt = :createdAt")
//    List<UserPost> searchPosts(@Param("title") String title, @Param("tag") String tag, @Param("createdAt") LocalDateTime createdAt);
// 7. 유저 아이디로 게시글 조회
    List<UserPost> selectByUserId(Long userId);
}