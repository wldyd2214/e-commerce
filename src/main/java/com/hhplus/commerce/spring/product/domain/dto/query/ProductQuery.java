package com.hhplus.commerce.spring.product.domain.dto.query;

import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

public class ProductQuery {

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    public static class SummeryList {
        private String productName;
        private Integer page;
        private Integer size;

        public static SummeryList of(String productName, Integer page, Integer size) {
            if (Objects.isNull(page)) page = 0;
            if (Objects.isNull(size)) size = 10;

            return SummeryList.builder()
                .productName(productName)
                .page(page)
                .size(size)
                .build();
        }
    }
}
