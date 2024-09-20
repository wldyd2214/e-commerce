package com.hhplus.commerce.spring.application.user.mapper;

import com.hhplus.commerce.spring.application.user.dto.request.UserPointChargeRequest;
import com.hhplus.commerce.spring.domain.user.dto.UserCommand;

public class UserFacadeRequestMapper {

    public static UserCommand.PointCharge toPointCharge(UserPointChargeRequest request) {
        return UserCommand.PointCharge.builder()
                                      .userId(request.getUserId())
                                      .chargePoint(request.getPoint())
                                      .build();
    }
}
