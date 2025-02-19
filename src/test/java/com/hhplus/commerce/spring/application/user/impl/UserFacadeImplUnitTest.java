package com.hhplus.commerce.spring.application.user.impl;

import com.hhplus.commerce.spring.application.user.UserFacade;
import com.hhplus.commerce.spring.application.user.dto.UserFacadeRequest;
import com.hhplus.commerce.spring.application.user.mapper.UserFacadeRequestMapper;
import com.hhplus.commerce.spring.application.user.mapper.UserFacadeResponseMapper;
import com.hhplus.commerce.spring.domain.payment.dto.PaymentCommand;
import com.hhplus.commerce.spring.domain.payment.service.PaymentService;
import com.hhplus.commerce.spring.domain.user.dto.UserCommand;
import com.hhplus.commerce.spring.domain.user.dto.UserInfo;
import com.hhplus.commerce.spring.domain.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class UserFacadeImplUnitTest {

    @Mock
    UserService userService;
    @Mock
    PaymentService paymentService;

    @Mock
    UserFacadeRequestMapper requestMapper;
    @Mock
    UserFacadeResponseMapper responseMapper;

    @InjectMocks
    UserFacade userFacade;

    @DisplayName("사용자 포인트 충전 비즈니스 로직 검증")
    @Test
    void chargeUserPoints() {

        // given
        long userId = 1;
        String name = "제리";
        BigDecimal point = new BigDecimal("100000");

        UserFacadeRequest.PointCharge request = new UserFacadeRequest.PointCharge(userId, point);

        PaymentCommand.Payment payment = new PaymentCommand.Payment(request .getUserId(), request.getPoint());
        UserCommand.PointCharge pointCharge = new UserCommand.PointCharge(request .getUserId(), request.getPoint());

        given(requestMapper.toPaymentCommand(request)).willReturn(payment);
        given(requestMapper.toUserCommand(request)).willReturn(pointCharge);
        given(paymentService.sendPayment(payment)).willReturn(Strings.EMPTY);

        UserInfo userInfo = new UserInfo(userId, name, point);
        given(userService.chargeUserPoints(pointCharge)).willReturn(userInfo);

        // when
        userFacade.chargeUserPoints(request);

        // then
        verify(userService, times(1)).findUserById(request.getUserId());
        verify(paymentService, times(1)).sendPayment(payment);
        verify(userService, times(1)).chargeUserPoints(pointCharge);
    }
}