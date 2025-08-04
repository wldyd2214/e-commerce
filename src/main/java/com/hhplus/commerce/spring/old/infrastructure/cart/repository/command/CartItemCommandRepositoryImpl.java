package com.hhplus.commerce.spring.old.infrastructure.cart.repository.command;

import com.hhplus.commerce.spring.old.domain.cart.model.CartItem;
import com.hhplus.commerce.spring.old.domain.cart.repository.command.CartItemCommandRepository;
import com.hhplus.commerce.spring.old.infrastructure.cart.repository.CartItemJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CartItemCommandRepositoryImpl implements CartItemCommandRepository {

    private final CartItemJpaRepository jpaRepository;

    @Override
    public CartItem save(CartItem cartItem) {
        return jpaRepository.save(cartItem);
    }

    @Override
    public List<CartItem> saveAll(List<CartItem> cartItems) {
        return jpaRepository.saveAll(cartItems);
    }
}
