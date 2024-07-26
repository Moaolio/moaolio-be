package com.example.side.Dto;

import lombok.*;

public class UserDto {
    @Getter
    @AllArgsConstructor
    @Builder
    @Setter
    @Data
    public static class UserDtoBuilder {
        private Long id;
        private String username;
    }
}
