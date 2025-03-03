package com.hhplus.commerce.spring.domain.cart.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemProductRes {

    private Long id;
    private String name;
    private String desc;
    private Integer price;
    private Integer count;
}
