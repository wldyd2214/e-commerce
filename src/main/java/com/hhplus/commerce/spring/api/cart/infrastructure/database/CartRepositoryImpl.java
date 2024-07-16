package com.hhplus.commerce.spring.api.cart.infrastructure.database;

import com.hhplus.commerce.spring.api.cart.model.Cart;
import com.hhplus.commerce.spring.api.cart.repository.CartRepository;
import com.hhplus.commerce.spring.api.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class CartRepositoryImpl implements CartRepository {
    private final CartJpaRepository jpaRepository;

    @Override
    public Optional<Cart> findByUser(User user) {
        return jpaRepository.findByUser(user);
    }

    @Override
    public Cart save(Cart saveCart) {
        return jpaRepository.save(saveCart);
    }
}
