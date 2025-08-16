package com.hhplus.commerce.spring.old.domain.cart.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartProduct {

    private Long id;
    @Transient
    private String name;
    @Transient
    private Integer price;

    public CartProduct(Long id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static CartProduct create(Long id, String name, Integer price) {
        return new CartProduct(id, name, price);
    }
}
