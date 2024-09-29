package com.hhplus.commerce.spring.application.user.impl;

import com.hhplus.commerce.spring.application.user.UserFacade;
import com.hhplus.commerce.spring.application.user.dto.UserInfo;
import com.hhplus.commerce.spring.application.user.dto.request.UserPointChargeRequest;
import com.hhplus.commerce.spring.application.user.mapper.UserFacadeRequestMapper;
import com.hhplus.commerce.spring.application.user.mapper.UserFacadeResponseMapper;
import com.hhplus.commerce.spring.domain.payment.service.PaymentService;
import com.hhplus.commerce.spring.domain.user.service.UserService;
import com.hhplus.commerce.spring.old.api.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;
    private final PaymentService paymentService;

    @Override
    @Transactional
    public UserInfo processUserPointCharge(UserPointChargeRequest request) {

        // 1. 사용자 포인트 충전
        User user = userService.chargeUserPoints(UserFacadeRequestMapper.toPointCharge(request));

        // 2. 결제 시스템 호출
        paymentService.sendPayment(UserFacadeRequestMapper.toPayment(request));

        return UserFacadeResponseMapper.toUserInfo(user);
    }
}
