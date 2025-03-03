package com.hhplus.commerce.spring.infrastructure.cart.repository;

import com.hhplus.commerce.spring.domain.cart.model.Cart;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartJpaRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserId(Long userId);
}
