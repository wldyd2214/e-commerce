package com.hhplus.commerce.spring.domain.order.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class OrderCommand {

    @Getter
    @AllArgsConstructor
    public static class Create {
        private Long userId;
        private List<OrderItem> orders;

        @Getter
        @AllArgsConstructor
        public static class OrderItem {
            private Long productId;
            private Integer orderCount;
        }
    }
}
