package com.hhplus.commerce.spring.api.order.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EventStatus {
    INIT("생성"),
    COMPLETED("완료");

    private final String desc;
}
