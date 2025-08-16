package com.hhplus.commerce.spring.user.domain.command;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

public class UserCommand {

    @Getter
    public static class ChargePoint {
        private Long userId;
        private BigDecimal chargePoint;

        @Builder(access = AccessLevel.PRIVATE)
        private ChargePoint(Long userId, BigDecimal chargePoint) {
            this.userId = userId;
            this.chargePoint = chargePoint;
        }

        public static UserCommand.ChargePoint of(Long userId, BigDecimal chargePoint) {
            return ChargePoint.builder()
                              .userId(userId)
                              .chargePoint(chargePoint)
                              .build();
        }
    }
}
