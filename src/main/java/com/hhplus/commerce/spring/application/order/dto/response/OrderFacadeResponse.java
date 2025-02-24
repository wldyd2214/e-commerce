package com.hhplus.commerce.spring.application.order.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class OrderFacadeResponse {

    @Getter
    @AllArgsConstructor
    public static class Create {
        private Long id;
        private Long userId;
        private List<OrderItemFacadeDTO> orders;
    }
}
