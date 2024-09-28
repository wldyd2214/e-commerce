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

        User user = userService.chargeUserPoints(UserFacadeRequestMapper.toPointCharge(request));

        paymentService.sendPayment(UserFacadeRequestMapper.toPayment(request));

        return UserFacadeResponseMapper.toUserInfo(user);
    }
}
