package com.example.side.comment.repository;

import com.example.side.comment.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments> findByUserId(Long userId); // 유저의 댓글 찾기
    List<Comments> findByPostId(Long postId); // 글에 남겨진 댓글 찾기
    List<Comments> findByPostIdAndUserId(Long postId, Long userId); // 특정 유저가 글에 남긴 댓글 찾기
}