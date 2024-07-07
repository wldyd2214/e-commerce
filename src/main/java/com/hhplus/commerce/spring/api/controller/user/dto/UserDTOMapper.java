package com.hhplus.commerce.spring.api.controller.user.dto;

import com.hhplus.commerce.spring.api.controller.user.response.BalanceChargeResponse;

public class UserDTOMapper {

    public static BalanceChargeResponse createDummyBalanceChargeResponse(Long userId, Long chargeAmount) {
        return BalanceChargeResponse.builder()
                                    .userId(userId)
                                    .balanceAmount(chargeAmount)
                                    .build();
    }
}
