package com.hhplus.commerce.spring.support.email.infrastructure;

import com.hhplus.commerce.spring.support.email.EmailTemplateRenderer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailTemplateRendererImpl implements EmailTemplateRenderer {

    private static final String WELCOME_EMAIL_TEMPLATE_NAME = "welcome-email";

    private final SpringTemplateEngine templateEngine;

    @Override
    public String renderWelcomeEmailTemplate(Context context) {
        try {
            return templateEngine.process(WELCOME_EMAIL_TEMPLATE_NAME, context);
        } catch (Exception e) {
            log.error("[EmailTemplateRenderer.renderWelcomeEmailTemplate()] - 이메일 템플릿 로딩 실패: {}", e.getMessage());
            throw new RuntimeException("이메일 템플릿 렌더링 실패");
        }
    }
}
