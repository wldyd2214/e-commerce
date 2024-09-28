package com.hhplus.commerce.spring.old.api.user.controller.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class BalanceChargeResponse {

    private Long userId;
    private BigDecimal userPoint;
}
