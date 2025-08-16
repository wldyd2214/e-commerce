package com.hhplus.commerce.spring.old.domain.cart.repository.command;

import com.hhplus.commerce.spring.old.domain.cart.model.Cart;

public interface CartCommandRepository {

    Cart save(Cart cart);
}
