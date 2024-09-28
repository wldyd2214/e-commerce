package com.hhplus.commerce.spring.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

public class UserCommand {

    @Getter
    @Builder
    public static class PointCharge {
        private Long userId;
        private BigDecimal chargePoint;
    }
}
