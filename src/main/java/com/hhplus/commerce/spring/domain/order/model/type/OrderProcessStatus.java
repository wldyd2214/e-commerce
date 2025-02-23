package com.hhplus.commerce.spring.domain.order.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderProcessStatus {
    INIT("주문생성"),
    CANCELED("주문취소"),
    PAYMENT_COMPLETED("결재완료"),
    PAYMENT_FAILED("결제실패"),
    COMPLETED("처리완료");

    private final String desc;
}