package com.hhplus.commerce.spring.domain.cart.dto;

import java.util.List;

public record CartInfo(Long id, Long userId, List<CartItemInfo> cartItemInfos) {
}
