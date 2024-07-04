package com.hhplus.commerce.spring.api.controller.order.response;

import com.hhplus.commerce.spring.api.controller.order.dto.OrderDTO;
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

    private Long userId;
    private Integer balanceAmount;
    private List<OrderDTO> orderItems;
}
