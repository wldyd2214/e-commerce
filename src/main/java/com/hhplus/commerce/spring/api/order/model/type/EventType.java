package com.hhplus.commerce.spring.api.order.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventType {
    ORDER_CREATE("주문 생성");

    private final String desc;
}
