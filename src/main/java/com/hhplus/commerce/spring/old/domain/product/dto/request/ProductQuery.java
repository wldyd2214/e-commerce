package com.hhplus.commerce.spring.old.domain.product.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class ProductQuery {

    @Getter
    @AllArgsConstructor
    public static class Validation {
        private List<Product> products;
    }

    @Getter
    @AllArgsConstructor
    public static class Product {
        private Long productId;
        private Integer orderCount;
    }
}
