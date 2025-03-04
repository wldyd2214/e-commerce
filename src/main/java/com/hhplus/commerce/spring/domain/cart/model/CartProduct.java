package com.hhplus.commerce.spring.domain.cart.model;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartProduct {

    private Long productId;
    private String name;
    private int price;

    public CartProduct(Long productId, String name, int price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public static CartProduct create(Long productId, String name, int price) {
        return new CartProduct(productId, name, price);
    }
}
