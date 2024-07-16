package com.hhplus.commerce.spring.infrastructure.db;

import com.hhplus.commerce.spring.model.Cart;
import com.hhplus.commerce.spring.model.User;
import com.hhplus.commerce.spring.repository.CartRepository;
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
