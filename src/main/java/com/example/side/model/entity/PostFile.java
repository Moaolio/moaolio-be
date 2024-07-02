package com.example.side.model.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@jakarta.persistence.Table(name = "user_post_file")
@lombok.Getter
@lombok.Setter
@NoArgsConstructor
public class PostFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileUrl;
    private String fileType;
    private Long fileSize;
    private String fileOriginName;

    @ManyToOne
    @JoinColumn(name = "user_post_id")
    private Post post;
}
