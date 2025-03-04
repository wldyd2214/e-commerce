package com.hhplus.commerce.spring.domain.cart.model;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartUser {

    private Long userId;
    private String name;

    public CartUser(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public static CartUser create(Long userId, String name) {
        return new CartUser(userId, name);
    }
}
