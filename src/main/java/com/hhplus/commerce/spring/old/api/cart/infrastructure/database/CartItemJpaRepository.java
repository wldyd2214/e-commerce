package com.hhplus.commerce.spring.old.api.cart.infrastructure.database;

import com.hhplus.commerce.spring.old.api.cart.model.Cart;
import com.hhplus.commerce.spring.old.api.cart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemJpaRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByCart(Cart cart);

    void removeAllByIdIn(List<Long> cartItemKeys);
}
