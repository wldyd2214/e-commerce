package com.hhplus.commerce.spring.domain.cart.repository.query;

import com.hhplus.commerce.spring.domain.cart.model.Cart;
import java.util.Optional;

public interface CartQueryRepository {

    Optional<Cart> findByUserId(Long userId);
}
