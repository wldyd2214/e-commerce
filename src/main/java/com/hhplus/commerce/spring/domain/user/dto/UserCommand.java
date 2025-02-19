package com.hhplus.commerce.spring.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import lombok.Setter;

public class UserCommand {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class PointCharge {
        private Long userId;
        private BigDecimal chargePoint;
    }
}
