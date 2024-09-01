package com.hhplus.commerce.spring.old.api.user.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentStatus {
    COMPLETED("결재완료"),
    WAITING("결재대기"),
    FAILED("결제실패");

    private final String desc;
}
