package com.hhplus.commerce.spring.product.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductStatus {
    PENDING("등록 대기"),
    ACTIVE("등록 완료"),
    INACTIVE("비활성");

    private final String description;
}
