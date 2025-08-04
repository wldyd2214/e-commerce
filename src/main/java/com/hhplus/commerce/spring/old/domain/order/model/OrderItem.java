package com.hhplus.commerce.spring.old.domain.order.model;

import com.hhplus.commerce.spring.old.domain.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@AttributeOverrides({
    @AttributeOverride(name = "createdDateTime", column = @Column(name = "reg_date")),
    @AttributeOverride(name = "modifiedDateTime", column = @Column(name = "mod_date"))
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tb_order_item")
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Order order;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Long productId;

    @Column(name = "order_product_name", nullable = false)
    private String orderProductName;

    @Column(name = "order_product_price", nullable = false)
    private Integer orderProductPrice;

    @Column(name = "order_product_count", nullable = false)
    private Integer orderProductCount;

    public OrderItem(Order order, Long productId, String orderProductName,
        Integer orderProductPrice,
        Integer orderProductCount) {
        this.order = order;
        this.productId = productId;
        this.orderProductName = orderProductName;
        this.orderProductPrice = orderProductPrice;
        this.orderProductCount = orderProductCount;
    }

    public static OrderItem create(Order order, Long productId, String orderProductName,
        Integer orderProductPrice,
        Integer orderProductCount) {
        return new OrderItem(order, productId, orderProductName, orderProductPrice, orderProductCount);
    }
}
