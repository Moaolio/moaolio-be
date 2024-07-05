package com.example.side.model.entity;

import com.example.side.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "notification")
@NoArgsConstructor
/*
알림
1. (내가 작성한) 포스트에 댓글이 달렸을 때
알림을 누르면 그 글로 이동, 글 ID만 받아놓으면 -> 나중에...
 */
public class Notification extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title; // ~님이 댓글을 남겼습니다.
    private String discription; // "댓글 내용"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
