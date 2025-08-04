package com.hhplus.commerce.spring.old.domain.payment.domain;

import com.hhplus.commerce.spring.old.domain.payment.dto.type.PaymentState;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Payment {

    private String transactionId;
    private PaymentState status;
}
