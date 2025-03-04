package com.hhplus.commerce.spring.presentation.cart.dto;

import com.hhplus.commerce.spring.presentation.cart.dto.common.CartItemDTO;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CartRequest {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class AddItem {
        @Valid
        private List<CartItemDTO> cartItems;
    }
}
