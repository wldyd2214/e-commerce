package com.hhplus.commerce.spring.support.email;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    @Test
    void sendWelcomeEmail() {
        // given
        String toEmail = "test@example.com";
        String userName = "Test User";

        // when
        boolean result = emailService.sendWelcomeEmail(toEmail, userName);

        // that
        assertThat(result).isTrue();
    }
}