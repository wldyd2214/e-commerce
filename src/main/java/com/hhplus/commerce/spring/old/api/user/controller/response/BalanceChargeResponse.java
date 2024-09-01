package com.hhplus.commerce.spring.old.api.user.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BalanceChargeResponse {

    private Long userId;
    private int userPoint;
}
