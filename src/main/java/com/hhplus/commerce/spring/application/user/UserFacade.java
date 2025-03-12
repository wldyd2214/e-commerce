package com.hhplus.commerce.spring.application.user;

import com.hhplus.commerce.spring.application.user.dto.UserFacadeRequest;
import com.hhplus.commerce.spring.application.user.dto.UserFacadeResponse;
import com.hhplus.commerce.spring.application.user.mapper.UserFacadeRequestMapper;
import com.hhplus.commerce.spring.application.user.mapper.UserFacadeResponseMapper;
import com.hhplus.commerce.spring.domain.payment.dto.PaymentCommand;
import com.hhplus.commerce.spring.domain.payment.service.PaymentService;
import com.hhplus.commerce.spring.domain.user.dto.UserCommand;
import com.hhplus.commerce.spring.domain.user.dto.UserInfo;
import com.hhplus.commerce.spring.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserFacade {

    private final UserService userService;
    private final PaymentService paymentService;

    private final UserFacadeRequestMapper requestMapper;
    private final UserFacadeResponseMapper responseMapper;

    @Transactional
    public UserFacadeResponse.PointCharge chargeUserPoints(UserFacadeRequest.PointCharge pointCharge) {

        // 1. 사용자 유효성 체크
        userService.findUserInfoById(pointCharge.getUserId());

        // 2. 결제 시스템 호출
        PaymentCommand.Payment paymentCommand = requestMapper.toPaymentCommand(pointCharge);
        paymentService.sendPayment(paymentCommand);

        // 3. 사용자 포인트 충전
        UserCommand.PointCharge chargeCommand = requestMapper.toUserCommand(pointCharge);
        UserInfo userInfo = userService.chargeUserPoints(chargeCommand);

        // 4. 응답 객체 변환
        return responseMapper.toUserInfo(userInfo);
    }
}
