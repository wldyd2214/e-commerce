package com.hhplus.commerce.spring.application.user.impl;

import com.hhplus.commerce.spring.application.user.dto.request.UserPointChargeRequest;
import com.hhplus.commerce.spring.domain.payment.dto.PaymentCommand;
import com.hhplus.commerce.spring.domain.payment.service.PaymentService;
import com.hhplus.commerce.spring.domain.user.dto.UserCommand;
import com.hhplus.commerce.spring.domain.user.service.UserService;
import com.hhplus.commerce.spring.old.api.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
@Transactional
class UserFacadeImplIntegrationTest {
    @Autowired
    private UserFacadeImpl userFacade;
    @MockBean
    private UserService userService;
    @MockBean
    private PaymentService paymentService;

    @DisplayName("사용자 포인트 충전 비즈니스 로직 호출 검증")
    @Test
    void processUserPointCharge() {
        // given
        long userId = 1;
        BigDecimal point = new BigDecimal("1000");
        UserPointChargeRequest request = new UserPointChargeRequest(userId, point);

        String name = "제리";
        given(userService.chargeUserPoints(any(UserCommand.PointCharge.class))).willReturn(createUser(userId, name, point));

        String transactionId = "txn-" + System.currentTimeMillis();
        given(paymentService.sendPayment(any(PaymentCommand.Payment.class))).willReturn(transactionId);

        // when
        userFacade.processUserPointCharge(request);

        // then
        verify(userService, times(1)).chargeUserPoints(any(UserCommand.PointCharge.class));
        verify(paymentService, times(1)).sendPayment(any(PaymentCommand.Payment.class));
    }

    private User createUser(Long id, String name, BigDecimal point) {
        return User.builder()
                   .id(id)
                   .name(name)
                   .point(point)
                   .build();
    }
}