package com.hhplus.commerce.spring.application.order.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderFacadeResponse {
    private Long id;
    private Long userId;
    private List<OrderItemFacadeDTO> orders;
}
