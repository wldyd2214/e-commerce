package com.hhplus.commerce.spring.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ProductInfo {
    private Long id;
    private String name;
    private String desc;
    private Integer price;
    private Integer stockCount;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
