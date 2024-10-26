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
        body += "<br>";
        body += "<br>";
        body += "<br>";
        body += "<img src='https://objectstorage.ap-seoul-1.oraclecloud.com/n/cnshljz7n2kf/b/moaolioBucket/o/mail%2Flogo.png' alt='logo','margin-right: 10px';>"
                +"<img src='https://objectstorage.ap-seoul-1.oraclecloud.com/n/cnshljz7n2kf/b/moaolioBucket/o/mail%2Flogo_right.png' alt='right_logo'>";
        body += "<br>";
        body += "<br>";
        body += "<br>";
        body += "<br>";
        body += "<br>";
        body += "<br>";
      //  body += "<img src='https://objectstorage.ap-seoul-1.oraclecloud.com/n/cnshljz7n2kf/b/moaolioBucket/o/mail%2Fpatition.png' 'margin-right: 10px';>"+
        body +="<img src='https://objectstorage.ap-seoul-1.oraclecloud.com/n/cnshljz7n2kf/b/moaolioBucket/o/mail%2Ftitle.png'>";
        body += "<br>";
        body += "<br>";
        body += "<br>";
        body += "<br>";
        body += "<br>";
        body += "<img src='https://objectstorage.ap-seoul-1.oraclecloud.com/n/cnshljz7n2kf/b/moaolioBucket/o/mail%2Fcontent.png'>";
        body += "<br>";
        body += "<br>";
        body += "<br>";
        body += "<br>";
        body += "<div style='text-align: center; margin-top: 30px;'>";
        body += "<h1>인증번호 : " + number + "</h1>";
        body += "</div>";

        message.setContent(body, "text/html; charset=UTF-8"); // setText 대신 setContent 사용



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

