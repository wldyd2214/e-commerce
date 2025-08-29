package com.hhplus.commerce.spring.support.email.infrastructure;

import com.hhplus.commerce.spring.support.email.EmailSender;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailSenderImpl implements EmailSender {

    @Override
    public boolean sendEmail(String to, String subject, String content) {

//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
//
//        try {
//            helper.setTo(toEmail);
//            helper.setFrom(fromEmail);
//            helper.setSubject(subject);
//            helper.setText(body, true);
//        } catch (Exception e) {
//            log.error("[SesEmailSender.send()] - {} 이메일 전송 실패: {}", toEmail, e.getMessage());
//            throw new RuntimeException("이메일 전송 실패");
//        }
//
//        mailSender.send(mimeMessage);

        return false;
    }
}
