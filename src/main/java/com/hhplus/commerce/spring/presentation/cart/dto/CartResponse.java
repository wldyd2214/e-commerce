package com.hhplus.commerce.spring.presentation.cart.dto;

import com.hhplus.commerce.spring.presentation.cart.dto.common.CartItemDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class CartResponse {

    @AllArgsConstructor
    @Getter
    public static class AddCartItem {
        private Long id;
        private List<CartItemDTO> items;
    }
}
