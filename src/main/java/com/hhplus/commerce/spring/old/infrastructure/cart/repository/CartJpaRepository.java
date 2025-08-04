package com.hhplus.commerce.spring.old.infrastructure.cart.repository;

import com.hhplus.commerce.spring.old.domain.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartJpaRepository extends JpaRepository<Cart, Long> {
}
