package com.example.side.email;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class VerificationService {
    // 인증번호를 저장하는 맵
    private final Map<String, String> verificationCodes = new HashMap<>();

    // 인증번호 저장
    public void saveVerificationCode(String email, String code) {
        verificationCodes.put(email, code);
    }

    // 인증번호 확인
    public boolean verifyCode(String email, String code) {
        String storedCode = verificationCodes.get(email);
        return storedCode != null && storedCode.equals(code);
    }
}
