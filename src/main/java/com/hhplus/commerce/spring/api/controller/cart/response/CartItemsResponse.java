package com.hhplus.commerce.spring.api.controller.cart.response;

import com.hhplus.commerce.spring.api.controller.cart.dto.CartItemDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemsResponse {

    private List<CartItemDTO> cartItems;
}
