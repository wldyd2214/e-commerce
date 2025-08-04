package com.hhplus.commerce.spring.old.infrastructure.cart.repository;

import com.hhplus.commerce.spring.old.domain.cart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemJpaRepository extends JpaRepository<CartItem, Long> {

}
