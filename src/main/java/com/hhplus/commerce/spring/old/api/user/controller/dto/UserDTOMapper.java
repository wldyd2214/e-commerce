package com.hhplus.commerce.spring.old.api.user.controller.dto;

import com.hhplus.commerce.spring.old.api.user.controller.response.BalanceChargeResponse;
import com.hhplus.commerce.spring.old.api.user.model.User;

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
                                    .userPoint(user.getPoint())
                                    .build();
    }
}
