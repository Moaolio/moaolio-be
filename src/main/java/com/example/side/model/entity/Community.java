package com.example.side.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "community")
@NoArgsConstructor
/*
게시판 종류별로 나누는 것
 */
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String occupation;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
