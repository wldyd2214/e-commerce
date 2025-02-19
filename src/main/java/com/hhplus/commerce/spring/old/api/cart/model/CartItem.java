package com.hhplus.commerce.spring.old.api.cart.model;

import com.hhplus.commerce.spring.domain.common.model.BaseEntity;
import com.hhplus.commerce.spring.domain.product.entity.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AttributeOverrides({
        /**
         * 공통 BaseTimeEntity의 각 필드를 테이블별로 다른 컬럼명으로 사용하기 위한 설정.
         */
        @AttributeOverride(name = "createdDateTime", column = @Column(name = "reg_date")),
        @AttributeOverride(name = "modifiedDateTime", column = @Column(name = "mod_date"))
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tb_cart_item")
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Product product;

    @Column(name = "cart_item_product_count", nullable = false)
    private Integer cartItemProductCount;

    @Builder
    public CartItem(Cart cart, Product product, Integer cartItemProductCount) {
        this.cart = cart;
        this.product = product;
        this.cartItemProductCount = cartItemProductCount;
    }

    public static CartItem create(Cart cart, Product product, Integer cartItemCount) {
        return CartItem.builder()
                       .cart(cart)
                       .product(product)
                       .cartItemProductCount(cartItemCount)
                       .build();
    }
}
