package com.hhplus.commerce.spring.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import lombok.Setter;

public class UserCommand {

    @Getter
    @AllArgsConstructor
    public static class PointCharge {
        private Long userId;
        private BigDecimal chargePoint;
    }

    @Getter
    @AllArgsConstructor
    public static class RewardPoint {
        private Long userId;
        private Integer deductionPoints;
    }
}
