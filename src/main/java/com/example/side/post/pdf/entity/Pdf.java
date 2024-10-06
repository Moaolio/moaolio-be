package com.example.side.post.pdf.entity;

import com.example.side.post.entity.PortfolioPost;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pdf")
@NoArgsConstructor
public class Pdf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileUrl;
    private Long fileSize;
    private String fileDownloadUri;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PortfolioPost post;


}
