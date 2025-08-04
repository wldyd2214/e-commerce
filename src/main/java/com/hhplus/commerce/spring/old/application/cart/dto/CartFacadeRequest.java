package com.hhplus.commerce.spring.old.application.cart.dto;

import com.hhplus.commerce.spring.old.application.cart.dto.common.CartItemFacadeDTO;
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
