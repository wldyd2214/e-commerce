package com.hhplus.commerce.spring.domain.cart.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CartItemInfo {
    private Long id;
    private CartProductInfo productInfo;
    private Integer orderCount;
}
