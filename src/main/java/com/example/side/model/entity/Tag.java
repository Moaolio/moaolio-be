package com.example.side.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Tag {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private String tagName;

    @JsonManagedReference
    @OneToMany(mappedBy = "tag", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Post> post;
}