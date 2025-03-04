package com.hhplus.commerce.spring.presentation.cart.dto.response;

import com.hhplus.commerce.spring.presentation.cart.dto.CartItemDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class CartResponse {

    @AllArgsConstructor
    @Getter
    public static class CartInfo {
        private Long id;
        private List<CartItemDTO> cartItems;
    }
}
