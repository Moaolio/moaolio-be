package com.example.side.post.tag.repository;

import com.example.side.post.tag.entity.PostTag;
import com.example.side.post.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByTagName(String tagName);

    Optional<PostTag> findByName(String tagName);
}
