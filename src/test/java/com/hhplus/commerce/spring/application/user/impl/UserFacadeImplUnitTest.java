package com.hhplus.commerce.spring.application.user.impl;

import com.hhplus.commerce.spring.application.user.dto.UserInfo;
import com.hhplus.commerce.spring.application.user.dto.request.UserPointChargeRequest;
import com.hhplus.commerce.spring.domain.payment.dto.PaymentCommand;
import com.hhplus.commerce.spring.domain.payment.service.PaymentService;
import com.hhplus.commerce.spring.domain.payment.service.PaymentServiceImpl;
import com.hhplus.commerce.spring.domain.user.dto.UserCommand;
import com.hhplus.commerce.spring.domain.user.service.UserService;
import com.hhplus.commerce.spring.infrastructure.payment.client.PaymentSystemClient;
import com.hhplus.commerce.spring.old.api.user.model.User;
import com.hhplus.commerce.spring.presentation.common.exception.CustomBadGateWayException;
import com.hhplus.commerce.spring.presentation.common.exception.code.BadGateWayErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class UserFacadeImplUnitTest {
    @Mock
    UserService userService;
    @Mock
    PaymentService paymentService;
    @InjectMocks
    UserFacadeImpl userFacade;

    @DisplayName("사용자 포인트 충전 비즈니스 로직 호출 검증")
    @Test
    void testProcessUserPointCharge() {
        // given
        long userId = 1;
        BigDecimal point = new BigDecimal("1000");
        UserPointChargeRequest request = createUserPointChargeRequest(userId, point);

        String name = "제리";
        given(userService.chargeUserPoints(any(UserCommand.PointCharge.class))).willReturn(createUser(userId, name, point));

        String transactionId = createTransactionId();
        given(paymentService.sendPayment(any(PaymentCommand.Payment.class))).willReturn(transactionId);
        
        // when
        userFacade.processUserPointCharge(request);
        
        // then
        verify(userService, times(1)).chargeUserPoints(any(UserCommand.PointCharge.class));
        verify(paymentService, times(1)).sendPayment(any(PaymentCommand.Payment.class));
    }

    private UserPointChargeRequest createUserPointChargeRequest(Long userId, BigDecimal point) {
        return UserPointChargeRequest.builder()
                                     .userId(userId)
                                     .point(point)
                                     .build();
    }

    private User createUser(Long id, String name, BigDecimal point) {
        return User.builder()
                   .id(id)
                   .name(name)
                   .point(point)
                   .build();
    }

    private String createTransactionId() {
        return "txn-" + System.currentTimeMillis();
    }
}