package com.hhplus.commerce.spring.infrastructure.cart.repository;

import com.hhplus.commerce.spring.domain.cart.model.Cart;
import com.hhplus.commerce.spring.domain.cart.repository.query.CartQueryRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CartQueryRepositoryImpl implements CartQueryRepository {

    private final CartJpaRepository jpaRepository;

    @Override
    public Optional<Cart> findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId);
    }
}
