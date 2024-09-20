package com.hhplus.commerce.spring.domain.product.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductInfo {
    private Long id;
    private String name;
    private String desc;
    /**
     * 상품 가격의 경우 정산시 소수점 단위로 떨어질 수 있기 때문에 객체 타입 변경이 필요해 보임...
     */
    private Integer price;
    private Integer stock;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
