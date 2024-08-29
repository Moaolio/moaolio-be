package com.example.side.notification.entity;

import com.example.side.common.BaseEntity;
import com.example.side.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Table(name = "notification")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private String description; // "댓글 내용"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
