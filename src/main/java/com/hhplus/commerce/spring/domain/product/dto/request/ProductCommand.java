package com.hhplus.commerce.spring.domain.product.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class ProductCommand {

    @Getter
    @AllArgsConstructor
    public static class Deduct {
        private List<DeductProduct> products;
    }

    @Getter
    @AllArgsConstructor
    public static class DeductProduct {
        private Long id;
        private Integer count;
    }
}
