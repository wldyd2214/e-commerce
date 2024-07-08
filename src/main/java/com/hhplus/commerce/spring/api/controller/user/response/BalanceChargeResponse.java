package com.hhplus.commerce.spring.api.controller.user.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BalanceChargeResponse {

    private Long userId;
    private int userPoint;
}
