package com.hhplus.commerce.spring.application.cart.dto.request;

import com.hhplus.commerce.spring.application.cart.dto.CartItemFacadeDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class CartFacadeRequest {

    @AllArgsConstructor
    @Getter
    public static class AddItem {
        private Long userId;
        private List<CartItemFacadeDTO> cartItems;
    }
}
