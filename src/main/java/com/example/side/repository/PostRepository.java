package com.example.side.repository;

import com.example.side.model.entity.Post;
import com.example.side.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();
    List<Post> findAllByUserOrderByCreatedAtDesc(User user);

    @Query("SELECT p FROM Post p WHERE :tag MEMBER OF p.tags AND p.category.name = :category AND p.createdAt BETWEEN :startDate AND :endDate")
    List<Post> findByTagCategoryAndCreationDate(String tag, String category, LocalDate startDate, LocalDate endDate);
}
