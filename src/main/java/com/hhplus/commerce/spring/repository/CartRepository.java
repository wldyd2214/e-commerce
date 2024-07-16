package com.hhplus.commerce.spring.repository;

import com.hhplus.commerce.spring.model.Cart;
import com.hhplus.commerce.spring.model.User;

import java.util.Optional;

public interface CartRepository {
    Optional<Cart> findByUser(User user);

    Cart save(Cart saveCart);
}
