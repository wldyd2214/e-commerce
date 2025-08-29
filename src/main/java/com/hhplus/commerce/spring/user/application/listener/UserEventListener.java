package com.hhplus.commerce.spring.user.application.listener;

import com.hhplus.commerce.spring.support.email.EmailService;
import com.hhplus.commerce.spring.user.domain.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventListener {

    private final EmailService emailService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendWelcomeEmail(UserCreatedEvent event) {
        emailService.sendEmail(event.getEmail(), "제목", "내용");
    }
}
