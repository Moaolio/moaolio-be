package com.example.side.post.file.repository;

import com.example.side.post.entity.Post;
import com.example.side.post.file.entity.PostFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostFileRepository extends JpaRepository<PostFile, Long> {
}