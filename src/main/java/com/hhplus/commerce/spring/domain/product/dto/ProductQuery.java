package com.hhplus.commerce.spring.domain.product.dto;

import lombok.Builder;
import lombok.Getter;

public class ProductQuery {

    @Getter
    @Builder
    public static class List {
        private Integer count;
        private Integer page;
        private String name;
    }
}
