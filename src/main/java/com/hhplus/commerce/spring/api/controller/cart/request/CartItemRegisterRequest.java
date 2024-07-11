package com.hhplus.commerce.spring.api.controller.cart.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemRegisterRequest {
    private Long productId;
    private Integer orderCount;
}
