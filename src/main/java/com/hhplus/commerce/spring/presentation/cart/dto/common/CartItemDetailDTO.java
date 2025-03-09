package com.hhplus.commerce.spring.presentation.cart.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartItemDetailDTO {

    private Long id;
    private Long productId;
    private String productName;
    private Integer orderCount;
}
