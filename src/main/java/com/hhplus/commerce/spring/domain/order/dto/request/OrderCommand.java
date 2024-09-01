package com.hhplus.commerce.spring.domain.order.dto.request;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

public class OrderCommand {

    @Getter
    @Builder
    public static class Order {
        private Long userId;
        private List<OrderItem> orders;

        @Getter
        @Builder
        public static class OrderItem {
            private Long productId;
            private Integer orderCount;
        }
    }
}
