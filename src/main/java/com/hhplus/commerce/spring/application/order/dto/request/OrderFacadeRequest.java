package com.hhplus.commerce.spring.application.order.dto.request;

import com.hhplus.commerce.spring.application.order.dto.OrderFacadeDTO;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderFacadeRequest {
    private Long userId;
    private List<OrderFacadeDTO> orders;
}
