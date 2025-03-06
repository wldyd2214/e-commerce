package com.hhplus.commerce.spring.infrastructure.cart.repository.command;

import com.hhplus.commerce.spring.domain.cart.model.Cart;
import com.hhplus.commerce.spring.domain.cart.repository.command.CartCommandRepository;
import com.hhplus.commerce.spring.infrastructure.cart.repository.CartJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Repository
public class CartCommandRepositoryImpl implements CartCommandRepository {

    private final CartJpaRepository jpaRepository;

    @Override
    public Cart save(Cart cart) {
        return jpaRepository.save(cart);
    }
}
