package com.hhplus.commerce.spring.old.domain.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ProductQuery {

    @Getter
    @AllArgsConstructor
    public static class Paged {
        private Integer count;
        private Integer page;
        private String name;
    }
}
