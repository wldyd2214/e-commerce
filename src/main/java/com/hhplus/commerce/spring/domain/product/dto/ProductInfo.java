package com.hhplus.commerce.spring.domain.product.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ProductInfo {
    private Long id;
    private String name;
    private String desc;
    private Integer price;
    private Integer stockCount;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
