package com.hhplus.commerce.spring.domain.cart.model;

import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartUser {

    private Long id;

    public CartUser(Long id) {
        this.id = id;
    }

    public static CartUser create(Long id) {
        return new CartUser(id);
    }
}
