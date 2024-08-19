package com.example.side.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GlobalResDto<T> {
    private int status;
    private T data;
    private String msg;

    // 성공 시 응답
    public static <T> GlobalResDto<T> success(T data, String msg) {
        return new GlobalResDto<>(200, data, msg);
    }

    // 실패 시 응답
    public static <T> GlobalResDto<T> fail(int status, T data, String msg) {
        return new GlobalResDto<>(status, data, msg);
    }

    // 실패 시 응답 (데이터 없이 메시지만)
    public static GlobalResDto<String> fail(String msg) {
        return new GlobalResDto<>(400, null, msg);
    }
}
