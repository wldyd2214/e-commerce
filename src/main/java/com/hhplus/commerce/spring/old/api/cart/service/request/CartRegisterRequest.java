package com.hhplus.commerce.spring.old.api.cart.service.request;

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
