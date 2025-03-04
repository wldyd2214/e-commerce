package com.hhplus.commerce.spring.domain.cart.dto.common;

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
