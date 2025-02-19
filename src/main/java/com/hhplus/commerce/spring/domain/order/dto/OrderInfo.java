package com.hhplus.commerce.spring.domain.order.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderInfo {
    private Long id;
    private Long userId;
    private List<OrderItemInfo> orders;

    @Getter
    @Builder
    public static class OrderItemInfo {
        private Long id;
        private String productName;
        private Integer productPrice;
        private Integer orderCount;
    }
}
