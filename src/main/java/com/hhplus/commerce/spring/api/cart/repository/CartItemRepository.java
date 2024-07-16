package com.hhplus.commerce.spring.api.cart.repository;

import com.hhplus.commerce.spring.api.cart.model.Cart;
import com.hhplus.commerce.spring.api.cart.model.CartItem;

import java.util.List;

public interface CartItemRepository {
    List<CartItem> findAllByCart(Cart cart);

    CartItem save(CartItem cartItem);

    void removeAllByIdIn(List<Long> cartItemKeys);
}
