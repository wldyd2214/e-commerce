package com.hhplus.commerce.spring.domain.cart.dto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartProductInfo {
    private Long id;
    private String name;
    private Integer price;
}
