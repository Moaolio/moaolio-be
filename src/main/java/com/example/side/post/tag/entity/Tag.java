package com.example.side.post.tag.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    // 태그 이름 반환 메서드
    @Column(name = "name")
    private String name;
    // 태그와 포스트의 관계를 나타내는 매핑 테이블




    public Tag() {}
}
