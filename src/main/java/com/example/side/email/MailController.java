package com.example.side.email;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MailController {

    private final MailService mailService;
    private final VerificationService verificationService;
    @ResponseBody
    @PostMapping("/auth/registar")
    public String emailCheck(@RequestBody MailDTO mailDTO) throws MessagingException, UnsupportedEncodingException {
        String email = mailDTO.getEmail().trim(); // 앞뒤 공백 제거
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address format: " + email);
        }
        return mailService.sendSimpleMessage(email);
    }

    private boolean isValidEmail(String email) {
        // 이메일 패턴 정규식
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(emailRegex);
    }

    @ResponseBody
    @PostMapping("/auth/verify")
    public String verifyCode(@RequestParam("email") String email, @RequestParam("code") String code) {
        boolean isValid = verificationService.verifyCode(email, code);
        if (isValid) {
            return "인증 성공";
        } else {
            return "인증 실패";
        }
    }
}

