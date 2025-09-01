package com.hhplus.commerce.spring.support.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private static final String WELCOME_EMAIL_TITLE = "e-commerce에 오신 것을 환영합니다!";

    private final EmailSender emailSender;
    private final EmailTemplateRenderer templateRenderer;

    public boolean sendWelcomeEmail(String to, String userName) {

        // 환영 이메일 내용 생성
        Context context = new Context();
        context.setVariable("userName", userName);
        String content = templateRenderer.renderWelcomeEmailTemplate(context);

        // 이메일 발송
        emailSender.sendEmail(to, WELCOME_EMAIL_TITLE, content);

        return true;
    }
}
