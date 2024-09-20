package com.hhplus.commerce.spring.infrastructure.order.entity;

import com.hhplus.commerce.spring.domain.common.model.BaseEntity;
import com.hhplus.commerce.spring.domain.order.model.OrderItem;
import com.hhplus.commerce.spring.domain.order.model.type.State;
import com.hhplus.commerce.spring.infrastructure.user.entity.UserEntity;
import com.hhplus.commerce.spring.old.api.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AttributeOverrides({
    /**
     * 공통 BaseTimeEntity의 각 필드를 테이블별로 다른 컬럼명으로 사용하기 위한 설정.
     */
    @AttributeOverride(name = "createdDateTime", column = @Column(name = "reg_date")),
    @AttributeOverride(name = "modifiedDateTime", column = @Column(name = "mod_date"))
})
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tb_order")
public class OrderEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity user;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private State state;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItem = new ArrayList<>();

    @Builder
    public OrderEntity(UserEntity user, State state) {
        this.user = user;
        this.state = state;
    }

    public static OrderEntity create(UserEntity user) {
        return OrderEntity.builder()
                          .user(user)
//                          .orderStatus(State.INIT)
                          .build();
    }

    public void orderStatusPaymentFail() {
        this.state = State.PAYMENT_FAILED;
    }

    public void orderStatusPaymentCompleted() {
        this.state = State.COMPLETED;
    }
}
