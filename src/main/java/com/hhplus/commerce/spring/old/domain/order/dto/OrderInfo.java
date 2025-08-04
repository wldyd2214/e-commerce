package com.hhplus.commerce.spring.old.domain.order.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderInfo {
    private Long id;
    private Long userId;
    private List<OrderItemInfo> orderItems;

    @Getter
    @AllArgsConstructor
    public static class OrderItemInfo {
        private Long id;
        private String productName;
        private Integer productPrice;
        private Integer orderCount;
    }
}
