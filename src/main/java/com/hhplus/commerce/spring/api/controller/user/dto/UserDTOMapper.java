package com.hhplus.commerce.spring.api.controller.user.dto;

import com.hhplus.commerce.spring.api.controller.user.response.BalanceChargeResponse;
import com.hhplus.commerce.spring.model.User;

public class UserDTOMapper {

    public static BalanceChargeResponse createDummyBalanceChargeResponse(Long userId, int chargePoint) {
        return BalanceChargeResponse.builder()
                                    .userId(userId)
                                    .userPoint(chargePoint)
                                    .build();
    }

    public static BalanceChargeResponse toBalanceChargeResponse(User user) {
        return BalanceChargeResponse.builder()
                                    .userId(user.getId())
                                    .userPoint(user.getUserPoint())
                                    .build();
    }
}
