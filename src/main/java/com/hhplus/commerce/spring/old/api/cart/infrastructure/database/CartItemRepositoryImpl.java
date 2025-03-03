package com.hhplus.commerce.spring.old.api.cart.infrastructure.database;

import com.hhplus.commerce.spring.old.api.cart.model.Cart;
import com.hhplus.commerce.spring.old.api.cart.model.CartItem;
import com.hhplus.commerce.spring.old.api.cart.repository.CartItemRepository;
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
    public void deleteAllByIdInBatch(List<Long> cartItemKeys) {
        jpaRepository.deleteAllByIdInBatch(cartItemKeys);
    }

    @Override
    public List<CartItem> saveAll(List<CartItem> cartItems) {
        return jpaRepository.saveAll(cartItems);
    }

    @Override
    public List<CartItem> findAllById(List<Long> cartItemKeys) {
        return jpaRepository.findAllById(cartItemKeys);
    }
}
