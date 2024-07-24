package com.hhplus.commerce.spring.api.product.model;

import com.hhplus.commerce.spring.api.common.infrastructure.database.BaseEntity;
import com.hhplus.commerce.spring.api.common.presentation.exception.CustomBadRequestException;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.coyote.BadRequestException;

import static com.hhplus.commerce.spring.api.common.presentation.exception.code.BadRequestErrorCode.PRODUCT_STOCK_BAD_REQUEST;

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
    private String productName;

    @Column(name = "product_desc", nullable = false)
    private String productDesc;

    @Column(name = "product_price", nullable = false)
    private Integer productPrice;

    @Column(name = "product_count", nullable = false)
    private Integer productCount;

    @Builder
    public Product(Long id, String productName, String productDesc, Integer productPrice,
        Integer productCount) {
        this.id = id;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productPrice = productPrice;
        this.productCount = productCount;
    }

    public boolean isQuantityLessThan(int quantity) {
        return this.productCount < quantity;
    }

    public void deductQuantity(int quantity) {
        if (isQuantityLessThan(quantity)) {
            throw new CustomBadRequestException(PRODUCT_STOCK_BAD_REQUEST);
        }
        this.productCount -= quantity;
    }
}
