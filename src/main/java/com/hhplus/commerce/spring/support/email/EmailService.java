package com.hhplus.commerce.spring.support.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailSender emailSender;
    private final EmailTemplate emailTemplate;

    public boolean sendEmail(String to, String subject, String body) {
        // 이메일 발송
        emailSender.sendEmail(to, subject, body);
        log.info("[sendEmail] - 이메일 발송 성공!");
        return true;
    }
}
