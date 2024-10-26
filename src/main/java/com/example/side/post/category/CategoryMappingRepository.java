package com.example.side.post.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryMappingRepository extends JpaRepository<CategoryMapping, Long> {
    CategoryMapping findByCategoryId(String name);
}
