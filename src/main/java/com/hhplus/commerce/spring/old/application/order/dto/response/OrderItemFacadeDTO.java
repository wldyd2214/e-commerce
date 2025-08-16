package com.hhplus.commerce.spring.old.application.order.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItemFacadeDTO {
    private Long id;
    private String productName;
    private Integer productPrice;
    private Integer orderCount;
}
