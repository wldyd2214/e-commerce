package com.hhplus.commerce.spring.api.service.cart.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartRegisterRequest {

    private List<CartItemRegister> cartItems;
}
