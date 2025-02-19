package com.hhplus.commerce.spring.old.api.cart.service.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemServiceRes {

    private Long id;
    private CartItemProductRes product;
    private Integer addProductCount;
}
