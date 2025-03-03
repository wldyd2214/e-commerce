package com.hhplus.commerce.spring.infrastructure.cart.repository;

import com.hhplus.commerce.spring.domain.cart.model.Cart;
import com.hhplus.commerce.spring.domain.cart.repository.command.CartCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@RequiredArgsConstructor
@Repository
public class CartCommandRepositoryImpl implements CartCommandRepository {

    @Override
    public Cart save(Cart saveCart) {
        return null;
    }
}
