package com.hhplus.commerce.spring.repository;

import com.hhplus.commerce.spring.model.Cart;
import com.hhplus.commerce.spring.model.CartItem;
import com.hhplus.commerce.spring.model.User;

import java.util.List;

public interface CartItemRepository {
    List<CartItem> findAllByCart(Cart cart);

    CartItem save(CartItem cartItem);

    void removeAllByIdIn(List<Long> cartItemKeys);
}
