package com.example.side.model.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@jakarta.persistence.Table(name = "user_scrap")
@lombok.Getter
@lombok.Setter
@NoArgsConstructor
public class UserScrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "user_post_id")
    private Post post;
}
