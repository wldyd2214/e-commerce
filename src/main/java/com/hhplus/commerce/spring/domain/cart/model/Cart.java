package com.hhplus.commerce.spring.domain.cart.model;

import com.hhplus.commerce.spring.domain.common.model.BaseEntity;
import com.hhplus.commerce.spring.domain.user.entity.User;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
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
@Table(name = "tb_cart")
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    private Long id;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
//    private User user;
    @Column(name = "user_id")
    private Long userId;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    public Cart(Long userId) {
        this.userId = userId;
    }

    public static Cart create(Long userId) {
        return new Cart(userId);
    }
}
