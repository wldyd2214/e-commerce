package com.hhplus.commerce.spring.domain.order.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
    private Long id;
    private Long productId;
    private Integer orderCount;
}
