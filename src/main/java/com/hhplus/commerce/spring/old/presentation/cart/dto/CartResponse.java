package com.hhplus.commerce.spring.old.presentation.cart.dto;

import com.hhplus.commerce.spring.old.presentation.cart.dto.common.CartItemDetailDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class CartResponse {

    @AllArgsConstructor
    @Getter
    public static class AddCartItem {
        private Long id;
        private List<CartItemDetailDTO> items;
    }

    @AllArgsConstructor
    @Getter
    public static class Cart {
        private Long id;
        private List<CartItemDetailDTO> items;
    }
}
