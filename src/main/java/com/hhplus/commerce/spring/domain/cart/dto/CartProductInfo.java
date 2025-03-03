package com.hhplus.commerce.spring.domain.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartProductInfo {
    private Long id;
    private String name;
    private String desc;
    private Integer price;
}
