package com.hhplus.commerce.spring.api.order.controller.response;

import com.hhplus.commerce.spring.api.order.controller.dto.OrderDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderResponse {

    @Schema(description = "주문 정보")
    private OrderDTO order;
}
