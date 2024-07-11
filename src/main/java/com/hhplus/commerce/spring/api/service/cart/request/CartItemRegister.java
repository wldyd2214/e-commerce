package com.hhplus.commerce.spring.api.service.cart.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemRegister {

    private Long productId;
    private Integer orderCount;
}
