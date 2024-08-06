package com.example.side.auth.jwt;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class RefreshToken {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id")
    private Long id;

    private String username;

    private String refresh;

    private String expiration;

    public RefreshToken(String username, String refresh, String expiration) {
        this.username = username;
        this.refresh = refresh;
        this.expiration = expiration;
    }
}
