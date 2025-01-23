package com.hhplus.commerce.spring.infrastructure.order.entity;

import com.hhplus.commerce.spring.domain.common.model.BaseEntity;
import com.hhplus.commerce.spring.domain.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AttributeOverrides({
    @AttributeOverride(name = "createdDateTime", column = @Column(name = "reg_date")),
    @AttributeOverride(name = "modifiedDateTime", column = @Column(name = "mod_date"))
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tb_order_item")
public class OrderItemEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Product product;

    @Column(name = "order_product_name", nullable = false)
    private String orderProductName;

    @Column(name = "order_product_price", nullable = false)
    private Integer orderProductPrice;

    @Column(name = "order_product_count", nullable = false)
    private Integer orderProductCount;

    @Builder
    public OrderItemEntity(OrderEntity order, Product product, String orderProductName,
                           Integer orderProductPrice,
                           Integer orderProductCount) {
        this.order = order;
        this.product = product;
        this.orderProductName = orderProductName;
        this.orderProductPrice = orderProductPrice;
        this.orderProductCount = orderProductCount;
    }

    public static OrderItemEntity create(OrderEntity order, Product product, int orderCount) {
        return OrderItemEntity.builder()
                              .order(order)
                              .product(product)
                              .orderProductName(product.getName())
                              .orderProductPrice(product.getPrice())
                              .orderProductCount(orderCount)
                              .build();
    }

    public static OrderItemEntity create(Product product, int orderCount) {
        return OrderItemEntity.builder()
                              .product(product)
                              .orderProductName(product.getName())
                              .orderProductPrice(product.getPrice())
                              .orderProductCount(orderCount)
                              .build();
    }
}
