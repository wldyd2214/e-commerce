package com.hhplus.commerce.spring.application.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Builder
public class UserPointChargeRequest {

    private Long userId;
    private BigDecimal point;
}
