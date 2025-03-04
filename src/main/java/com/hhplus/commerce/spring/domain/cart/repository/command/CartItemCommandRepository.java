package com.hhplus.commerce.spring.domain.cart.repository.command;

import com.hhplus.commerce.spring.domain.cart.model.CartItem;

import java.util.List;

public interface CartItemCommandRepository {

    CartItem save(CartItem cartItem);

    List<CartItem> saveAll(List<CartItem> cartItems);
}
