package com.hhplus.commerce.spring.old.domain.cart.model;

import com.hhplus.commerce.spring.old.domain.common.model.BaseEntity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
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
@Table(name = "tb_cart")
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    private Long id;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "user_id"))
    })
    private CartUser user;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    public Cart(CartUser user) {
        this.user = user;
    }

    public static Cart create(CartUser user) {
        return new Cart(user);
    }


    public void updateCartItems(List<CartItem> newCartItems) {
        this.cartItems.clear(); // 기존 리스트 초기화
        newCartItems.forEach(item -> item.assignCart(this)); // CartItem의 cart 설정
        this.cartItems.addAll(newCartItems);
    }

    public void removeCartItems(List<Long> cartItemIds) {
        this.cartItems.removeIf(cartItem -> cartItemIds.contains(cartItem.getId()));
    }
}
