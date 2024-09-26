package com.example.side.post.entity;

import lombok.Getter;

@Getter
public enum PostType {
    PORTFOLIO("0"),
    COMMUNITY("1");

    private final String value;

    PostType(String value) {
        this.value = value;
    }

    public static PostType fromValue(String value) {
        for (PostType type : values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid PostType value: " + value);
    }
}