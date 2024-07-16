package com.example.side.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Tag {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String tagName;
}