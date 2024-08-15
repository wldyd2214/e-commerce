package com.hhplus.commerce.spring.api.order.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AggregateType {
    ORDER("주문 도메인");

    private final String desc;
}
