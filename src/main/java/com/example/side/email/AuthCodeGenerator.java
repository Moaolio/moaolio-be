package com.example.side.email;

import java.util.Random;

public class AuthCodeGenerator {
    public static String generateCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // 6자리 숫자 코드 생성
        return String.valueOf(code);
    }
}
