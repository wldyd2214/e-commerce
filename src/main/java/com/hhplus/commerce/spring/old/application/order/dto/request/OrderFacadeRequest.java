package com.hhplus.commerce.spring.old.application.order.dto.request;

import com.hhplus.commerce.spring.old.application.order.dto.OrderFacadeDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class OrderFacadeRequest {

    @Getter
    @AllArgsConstructor
    public static class Create {
        private Long userId;
        private List<OrderFacadeDTO> orders;
    }
}
