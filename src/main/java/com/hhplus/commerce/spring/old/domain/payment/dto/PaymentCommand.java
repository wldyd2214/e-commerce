package com.hhplus.commerce.spring.old.domain.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import lombok.Setter;

public class PaymentCommand {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Payment {
        private Long userId;
        private BigDecimal amount;
    }
}
