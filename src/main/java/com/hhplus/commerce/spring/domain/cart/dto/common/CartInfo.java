package com.hhplus.commerce.spring.domain.cart.dto.common;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CartInfo {

    private Long id;
    private CartUserInfo userInfo;
    private List<CartItemInfo> cartItemInfos;
}
