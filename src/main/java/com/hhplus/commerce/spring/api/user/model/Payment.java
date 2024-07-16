package com.hhplus.commerce.spring.api.user.model;

import com.hhplus.commerce.spring.api.order.model.Order;
import com.hhplus.commerce.spring.api.domain.common.infrastructure.BaseEntity;
import com.hhplus.commerce.spring.api.user.model.type.PaymentStatus;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "payment")
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Order order;

    @Column(name = "payment_price", nullable = false)
    private Integer paymentPrice;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    @Builder
    public Payment(Order order, Integer paymentPrice, PaymentStatus paymentStatus) {
        this.order = order;
        this.paymentPrice = paymentPrice;
        this.paymentStatus = paymentStatus;
    }

    public static Payment create(Order order, int price) {
        return Payment.builder()
                      .order(order)
                      .paymentPrice(price)
                      .build();
    }

    public void paymentStatusFailed() { this.paymentStatus = PaymentStatus.FAILED; }
    public void paymentStatusCompleted() { this.paymentStatus = PaymentStatus.COMPLETED; }
}
