package com.hhplus.commerce.spring.old.domain.product.model;

import com.hhplus.commerce.spring.old.domain.common.model.BaseEntity;
import com.hhplus.commerce.spring.old.presentation.common.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.old.presentation.common.exception.code.BadRequestErrorCode;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "tb_product")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "product_desc", nullable = false)
    private String desc;

    @Column(name = "product_price", nullable = false)
    private Integer price;

    @Column(name = "product_count", nullable = false)
    private Integer count;

    public Product(String name, String desc, Integer price, Integer count) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.count = count;
    }

    public static Product createProduct(String name, String desc, Integer price, Integer count) {
        return new Product(name, desc, price, count);
    }

    public boolean isQuantityLessThan(int quantity) {
        return this.count < quantity;
    }

    public void deductQuantity(int quantity) {
        if (isQuantityLessThan(quantity)) {
            throw new CustomBadRequestException(BadRequestErrorCode.PRODUCT_STOCK_BAD_REQUEST);
        }
        this.count -= quantity;
    }
}
