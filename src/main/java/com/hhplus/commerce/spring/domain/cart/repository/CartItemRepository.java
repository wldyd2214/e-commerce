package com.hhplus.commerce.spring.domain.cart.repository;

import com.hhplus.commerce.spring.domain.cart.model.Cart;
import com.hhplus.commerce.spring.domain.cart.model.CartItem;

import java.util.List;

public interface CartItemRepository {
    List<CartItem> findAllByCart(Cart cart);

    CartItem save(CartItem cartItem);

    void deleteAllByIdInBatch(List<Long> cartItemKeys);

    List<CartItem> saveAll(List<CartItem> cartItems);

    List<CartItem> findAllById(List<Long> cartItemKeys);
}
