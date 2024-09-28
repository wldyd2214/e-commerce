package com.hhplus.commerce.spring.domain.payment.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

public class PaymentCommand {

    @Getter
    @Builder
    public static class Payment {
        private Long userId;
        private BigDecimal amount;
    }
}
