package com.hhplus.commerce.spring.old.api.cart.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemsRegisterRequest {
    private List<CartItemRegisterRequest> cartItems;
}
