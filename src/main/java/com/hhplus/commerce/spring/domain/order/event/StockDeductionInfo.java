package com.hhplus.commerce.spring.domain.order.event;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StockDeductionInfo {

    private Long productId;
    private Long deductStock;
}
