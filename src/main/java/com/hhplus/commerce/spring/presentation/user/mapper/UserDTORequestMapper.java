package com.hhplus.commerce.spring.presentation.user.mapper;

import com.hhplus.commerce.spring.application.user.dto.request.UserPointChargeRequest;

public class UserDTORequestMapper {

    public static UserPointChargeRequest toUserPointChargeRequest(Long userId, int chargePoint) {
        return UserPointChargeRequest.builder()
                                     .userId(userId)
                                     .point(chargePoint)
                                     .build();
    }
}
