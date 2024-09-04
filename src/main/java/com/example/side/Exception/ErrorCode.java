package com.example.side.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    NOT_FOUND_POST(HttpStatus.NOT_FOUND.value(), "P002", "포스트가 존재하지 않습니다."),
    NOT_FOUND_POSTLIKE(HttpStatus.NOT_FOUND.value(), "PL001", "좋아요한 이력이 존재하지 않습니다."),
    DUPLICATED_POSTLIEK(HttpStatus.BAD_REQUEST.value(), "PL002", "좋아요는 한번만 가능합니다.");

    private final int status;
    private final String code;
    private final String message;
}
