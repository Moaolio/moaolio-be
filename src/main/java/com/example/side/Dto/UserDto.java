package com.example.side.Dto;

import lombok.*;

@Getter
@Setter
public class UserDto {
//    @Getter
//    @AllArgsConstructor
//    @Builder
//    @Setter
//    @Data
//    public static class UserDtoBuilder {
//        private Long id;
//        private String username;
//    }
    private String role;
    private String nickname;
    private String username;
}
