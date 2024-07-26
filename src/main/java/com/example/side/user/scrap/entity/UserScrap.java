package com.example.side.user.scrap.entity;

import com.example.side.post.entity.Post;
import com.example.side.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_scrap")
@Getter
@Setter
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
