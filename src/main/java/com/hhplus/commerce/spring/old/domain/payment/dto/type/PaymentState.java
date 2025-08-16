package com.hhplus.commerce.spring.old.domain.payment.dto.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PaymentState {
    SUCCESS("결제성공"),
    FAILED("결제실패");

    private final String desc;
}
