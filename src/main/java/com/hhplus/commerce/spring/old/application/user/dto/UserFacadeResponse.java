package com.hhplus.commerce.spring.old.application.user.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class UserFacadeResponse {

    @AllArgsConstructor
    @Getter
    public static class PointCharge {
        private Long id;
        private String name;
        private BigDecimal point;
    }
}
