package com.hhplus.commerce.spring.user.domain.command;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

public class UserCommand {

    @Getter
    public static class Register {
        private String email;
        private String name;
        private String password;

        @Builder(access = AccessLevel.PRIVATE)
        private Register(String email, String name, String password) {
            this.email = email;
            this.name = name;
            this.password = password;
        }

        public static Register of(String email, String name, String password) {
            return Register.builder()
                .email(email)
                .name(name)
                .password(password)
                .build();
        }
    }

    @Getter
    public static class Login {
        private String email;
        private String password;

        @Builder(access = AccessLevel.PRIVATE)
        private Login(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public static Login of(String email, String password) {
            return Login.builder()
                .email(email)
                .password(password)
                .build();
        }
    }

    @Getter
    public static class ChargePoint {
        private Long userId;
        private BigDecimal chargePoint;

        @Builder(access = AccessLevel.PRIVATE)
        private ChargePoint(Long userId, BigDecimal chargePoint) {
            this.userId = userId;
            this.chargePoint = chargePoint;
        }

        public static ChargePoint of(Long userId, BigDecimal chargePoint) {
            return ChargePoint.builder()
                              .userId(userId)
                              .chargePoint(chargePoint)
                              .build();
        }
    }
}
