package com.hhplus.commerce.spring.application.user.impl;

import com.hhplus.commerce.spring.application.user.UserFacade;
import com.hhplus.commerce.spring.application.user.dto.UserInfo;
import com.hhplus.commerce.spring.application.user.dto.request.UserPointChargeRequest;
import com.hhplus.commerce.spring.application.user.mapper.UserFacadeRequestMapper;
import com.hhplus.commerce.spring.application.user.mapper.UserFacadeResponseMapper;
import com.hhplus.commerce.spring.domain.user.service.UserService;
import com.hhplus.commerce.spring.old.api.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;

    @Override
    public UserInfo processUserPointCharge(UserPointChargeRequest request) {
        User user = userService.chargeUserPoints(UserFacadeRequestMapper.toPointCharge(request));
        return UserFacadeResponseMapper.toUserInfo(user);
    }
}
