package com.example.side.banner.entitiy;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "banner")

public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String img;
    private String url;

    @CreatedDate
    private String createdAt;
    @LastModifiedDate
    private String updatedAt;

}
