package com.hhplus.commerce.spring.application.user.impl;

import com.hhplus.commerce.spring.application.user.dto.request.UserPointChargeRequest;
import com.hhplus.commerce.spring.domain.payment.dto.PaymentCommand;
import com.hhplus.commerce.spring.domain.payment.service.PaymentService;
import com.hhplus.commerce.spring.domain.user.dto.UserCommand;
import com.hhplus.commerce.spring.domain.user.service.UserService;
import com.hhplus.commerce.spring.infrastructure.payment.client.PaymentSystemClient;
import com.hhplus.commerce.spring.old.api.user.model.User;
import com.hhplus.commerce.spring.presentation.common.exception.CustomBadGateWayException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserFacadeImplUnitTest {
    @InjectMocks
    UserFacadeImpl userFacade;
    @Mock
    UserService userService;
    @Mock
    PaymentService paymentService;
    @Mock
    PaymentSystemClient client;

    @DisplayName("사용자 포인트 충전 비즈니스 로직 호출 검증")
    @Test
    void processUserPointCharge() {
        // given
        long userId = 1;
        BigDecimal point = new BigDecimal("1000");
        UserPointChargeRequest request = createUserPointChargeRequest(userId, point);

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

    @DisplayName("결제 시스템 연동 실패시 사용자 포인트 보상 트랜잭션 검증")
    @Test
    void paymentSystemCustomBadGateWayException() {
        // given
        long userId = 1;
        BigDecimal point = new BigDecimal("1000");
        UserPointChargeRequest request = createUserPointChargeRequest(userId, point);

        String name = "제리";
        given(userService.chargeUserPoints(any(UserCommand.PointCharge.class))).willReturn(createUser(userId, name, point));

        given(client.sendPayment(userId, point)).willThrow(CustomBadGateWayException.class);

        doCallRealMethod().when(paymentService).sendPayment(any());

//        String transactionId = "txn-" + System.currentTimeMillis();
//        given(paymentService.sendPayment(any(PaymentCommand.Payment.class))).willReturn(transactionId);

        // when
        try {
            userFacade.processUserPointCharge(request);
        } catch (CustomBadGateWayException e) {
            System.out.println("에러 발생!");
        }

        assertEquals();
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
}