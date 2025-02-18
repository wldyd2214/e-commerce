package com.hhplus.commerce.spring.application.user.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class UserFacadeRequest {

    @AllArgsConstructor
    @Getter
    public static class PointCharge {
        private Long userId;
        private BigDecimal point;
    }
}
