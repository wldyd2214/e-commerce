package com.hhplus.commerce.spring.infrastructure.db;

import com.hhplus.commerce.spring.model.Cart;
import com.hhplus.commerce.spring.model.CartItem;
import com.hhplus.commerce.spring.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CartItemRepositoryImpl implements CartItemRepository {
    private final CartItemJpaRepository jpaRepository;

    @Override
    public List<CartItem> findAllByCart(Cart cart) {
        return jpaRepository.findAllByCart(cart);
    }

    @Override
    public CartItem save(CartItem cartItem) {
        return jpaRepository.save(cartItem);
    }

    @Override
    public void removeAllByIdIn(List<Long> cartItemKeys) {
        jpaRepository.removeAllByIdIn(cartItemKeys);
    }
}
