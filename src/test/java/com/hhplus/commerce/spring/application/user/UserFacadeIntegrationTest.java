package com.hhplus.commerce.spring.application.user;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
@Transactional
class UserFacadeIntegrationTest {

    @Autowired
    private UserFacade userFacade;
    @MockBean
    private UserFacadeRequestMapper requestMapper;
    @MockBean
    private UserFacadeResponseMapper responseMapper;
    @MockBean
    private UserService userService;
    @MockBean
    private PaymentService paymentService;

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
        verify(userService, times(1)).findUserInfoById(request.getUserId());
        verify(paymentService, times(1)).sendPayment(payment);
        verify(userService, times(1)).chargeUserPoints(pointCharge);
    }
}