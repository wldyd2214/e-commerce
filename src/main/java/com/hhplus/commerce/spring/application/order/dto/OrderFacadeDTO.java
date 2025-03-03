package com.hhplus.commerce.spring.application.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderFacadeDTO {
    private Long productId;
    private Integer orderCount;
}
