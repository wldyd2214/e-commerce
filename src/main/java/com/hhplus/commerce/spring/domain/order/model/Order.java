package com.hhplus.commerce.spring.domain.order.model;

import com.hhplus.commerce.spring.application.order.event.OrderCreateEvent;
import com.hhplus.commerce.spring.common.Events;
import com.hhplus.commerce.spring.domain.common.model.BaseEntity;
import com.hhplus.commerce.spring.domain.order.model.type.OrderProcessStatus;
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
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderProcessStatus orderProcessStatus;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order(long userId, OrderProcessStatus orderProcessStatus) {
        this.userId = userId;
        this.orderProcessStatus = orderProcessStatus;
    }

    public static Order create(long userId) {

        Order order = new Order(userId, OrderProcessStatus.INIT);

        // 1. 상품 재고 차감
        // 2. 사용자 포인트 차감
        // 3. 외부 시스템 주문 생성 이벤트 발행
//        Events.raise(new OrderCreateEvent());

        return order;
    }

    public void updateOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void orderStatusPaymentFail() {
        this.orderProcessStatus = OrderProcessStatus.PAYMENT_FAILED;
    }

    public void orderStatusPaymentCompleted() {
        this.orderProcessStatus = OrderProcessStatus.COMPLETED;
    }
}
