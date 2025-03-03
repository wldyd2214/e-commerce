package com.hhplus.commerce.spring.application.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CartItemFacadeDTO {

    private Long productId;
    private Integer orderCount;
}
