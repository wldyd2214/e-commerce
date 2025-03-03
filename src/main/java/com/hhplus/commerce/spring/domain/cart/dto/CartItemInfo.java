package com.hhplus.commerce.spring.domain.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CartItemInfo {
    private CartProductInfo productInfo;
    private Integer orderCount;
}
