package com.example.side.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 자체 회원 가입 유저 DTO
 */
@Builder
@Getter
@Setter
public class UserSignUpRequest {

    /**
     * 영어 알파벳과 숫자만 입력할 수 있습니다.
     */
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    private String username;

    /**
     * 최소 하나의 숫자(0-9)가 포함되어야 합니다.
     * 최소 하나의 알파벳 문자(a-z, A-Z)가 포함되어야 합니다.
     * 최소 하나의 특수 문자(알파벳 문자, 숫자, 밑줄(_)이 아닌 문자)가 포함되어야 합니다.
     * 공백 문자가 포함되지 않아야 합니다.
     * 길이가 8자 이상 16자 이하여야 합니다.
     */
    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}")
    private String password;

    @NotBlank
    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$")
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String birth;

    /**
     * JOB, TECH, CAREER, BIRTH
     */

}
