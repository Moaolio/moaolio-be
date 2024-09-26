package com.example.side.post.category;

import jakarta.persistence.*;

@Entity
@Table(name = "category")
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Override
    public String toString() {
        return this.name; // 또는 다른 필드를 반환할 수 있습니다.
    }

}
