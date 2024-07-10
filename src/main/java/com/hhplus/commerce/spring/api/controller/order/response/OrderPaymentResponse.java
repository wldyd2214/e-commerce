package com.hhplus.commerce.spring.api.controller.order.response;

import com.hhplus.commerce.spring.api.controller.order.dto.OrderDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPaymentResponse {

    @Schema(description = "주문 목록")
    private List<OrderDTO> orderItems;
}
