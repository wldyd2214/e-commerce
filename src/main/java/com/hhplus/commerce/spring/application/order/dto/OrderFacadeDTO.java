package com.hhplus.commerce.spring.application.order.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderFacadeDTO {
    private Long productId;
    private Integer orderCount;
}
