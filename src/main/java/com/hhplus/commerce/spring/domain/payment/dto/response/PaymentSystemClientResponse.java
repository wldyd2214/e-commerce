package com.hhplus.commerce.spring.domain.payment.dto.response;

import com.hhplus.commerce.spring.domain.payment.dto.type.PaymentState;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaymentSystemClientResponse {
    private String transactionId;
    private PaymentState status;
}
