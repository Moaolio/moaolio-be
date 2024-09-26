package com.example.side.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private static final String senderEmail = "moaolio24@gmail.com";
    private final VerificationService verificationService;


    // 랜덤으로 숫자 생성
    public String createNumber() {
        Random random = new Random();
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < 8; i++) { // 인증 코드 8자리
            int index = random.nextInt(3); // 0~2까지 랜덤, 랜덤값으로 switch문 실행

            switch (index) {
                case 0 -> key.append((char) (random.nextInt(26) + 97)); // 소문자
                case 1 -> key.append((char) (random.nextInt(26) + 65)); // 대문자
                case 2 -> key.append(random.nextInt(10)); // 숫자
            }
        }
        return key.toString();
    }

    public MimeMessage createMail(String mail, String number) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.setFrom(senderEmail);
        message.setRecipients(MimeMessage.RecipientType.TO, mail);
        message.setSubject("모아올리오 메일인증 안내", "UTF-8");
        String body = "";
        body += "<div style='font-family: Arial, sans-serif; background-color: #f0f8ff; padding: 20px;'>";
        body += "<div style='text-align: center; margin-bottom: 20px;'>";
        body += "<img src='logo_url' alt='모아올리오' style='width: 150px;'/>";
        body += "<h2 style='color: #00AEEF;'>모아올리오 메일인증 안내</h2>";
        body += "</div>";
        body += "<h3>안녕하세요, 모아올리오를 이용해주셔서 감사합니다.</h3>";
        body += "<p>아래의 인증번호를 입력해주세요. 감사힙니다.</p>";
        body += "<div style='text-align: center; margin-top: 30px;'>";
        body += "<h1>" + number + "</h1>";
        message.setText(body, "UTF-8", "html");

        return message;
    }

    // 메일 발송
    public String sendSimpleMessage(String sendEmail) throws MessagingException {
        String number = createNumber(); // 랜덤 인증번호 생성
        verificationService.saveVerificationCode(sendEmail, number);

        MimeMessage message = createMail(sendEmail, number); // 메일 생성
        try {
            javaMailSender.send(message); // 메일 발송
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("메일 발송 중 오류가 발생했습니다.");
        }

        return number; // 생성된 인증번호 반환
    }
}

