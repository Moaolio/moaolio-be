package com.example.side.repository;

import com.example.side.model.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
    // UserPostComments 테이블에서 postId로 조회하는 메서드
    List<Comments> findByUserPostId(Long userPostId);

}