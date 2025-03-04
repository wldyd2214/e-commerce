package com.hhplus.commerce.spring.infrastructure.cart.repository;

import com.hhplus.commerce.spring.domain.cart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemJpaRepository extends JpaRepository<CartItem, Long> {

}
