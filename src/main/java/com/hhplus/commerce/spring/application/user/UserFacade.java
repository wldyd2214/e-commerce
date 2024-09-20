package com.hhplus.commerce.spring.application.user;

import com.hhplus.commerce.spring.application.user.dto.UserInfo;
import com.hhplus.commerce.spring.application.user.dto.request.UserPointChargeRequest;

public interface UserFacade {
    UserInfo processUserPointCharge(UserPointChargeRequest toUserPointChargeRequest);
}
