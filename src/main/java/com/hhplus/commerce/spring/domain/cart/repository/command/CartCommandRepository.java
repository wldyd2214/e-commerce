package com.hhplus.commerce.spring.domain.cart.repository.command;

import com.hhplus.commerce.spring.domain.cart.model.Cart;

public interface CartCommandRepository {

    Cart save(Cart cart);
}
