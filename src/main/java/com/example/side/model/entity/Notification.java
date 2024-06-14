package com.example.side.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@lombok.Setter
@lombok.Getter
@jakarta.persistence.Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String discription;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
