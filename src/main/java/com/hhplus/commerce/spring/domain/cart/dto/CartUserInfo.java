package com.hhplus.commerce.spring.domain.cart.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CartUserInfo {
    private Long id;
    private String name;
    private BigDecimal point;
}
