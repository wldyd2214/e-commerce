package com.hhplus.commerce.spring.presentation.cart.dto.request;

import com.hhplus.commerce.spring.presentation.cart.dto.CartItemDTO;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class CartRequest {

    @AllArgsConstructor
    @Getter
    public static class AddItem {
        @Valid
        private List<CartItemDTO> cartItems;
    }
}
