package com.hhplus.commerce.spring.infrastructure.db;

import com.hhplus.commerce.spring.model.Cart;
import com.hhplus.commerce.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartJpaRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
