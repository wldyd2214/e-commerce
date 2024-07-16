package com.hhplus.commerce.spring.api.cart.infrastructure.database;

import com.hhplus.commerce.spring.api.cart.model.Cart;
import com.hhplus.commerce.spring.api.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartJpaRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
