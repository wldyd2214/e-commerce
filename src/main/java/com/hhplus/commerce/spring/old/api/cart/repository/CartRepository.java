package com.hhplus.commerce.spring.old.api.cart.repository;

import com.hhplus.commerce.spring.old.api.cart.model.Cart;
import com.hhplus.commerce.spring.old.api.user.model.User;

import java.util.Optional;

public interface CartRepository {
    Optional<Cart> findByUser(User user);

    Cart save(Cart saveCart);
}
