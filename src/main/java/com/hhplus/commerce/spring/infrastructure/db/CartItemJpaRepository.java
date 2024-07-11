package com.hhplus.commerce.spring.infrastructure.db;

import com.hhplus.commerce.spring.model.Cart;
import com.hhplus.commerce.spring.model.CartItem;
import com.hhplus.commerce.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemJpaRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByCart(Cart cart);

    void removeAllByIdIn(List<Long> cartItemKeys);
}
