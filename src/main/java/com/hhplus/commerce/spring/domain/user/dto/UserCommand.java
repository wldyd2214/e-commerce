package com.hhplus.commerce.spring.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

public class UserCommand {

    @Getter
    @Builder
    public static class PointCharge {
        private Long userId;
        private Integer chargePoint;
    }
}
