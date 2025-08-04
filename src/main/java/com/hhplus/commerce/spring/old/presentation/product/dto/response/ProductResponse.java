package com.hhplus.commerce.spring.old.presentation.product.dto.response;

import com.hhplus.commerce.spring.old.presentation.product.dto.ProductDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class ProductResponse {

    @AllArgsConstructor
    @Getter
    public static class PagedProduct {

        @Schema(description = "전체 개수")
        private Integer totalCount;

        @Schema(description = "현재 페이지")
        private Integer currentPage;

        @Schema(description = "상품 목록 정보")
        private java.util.List<ProductDTO> products;
    }

    @AllArgsConstructor
    @Getter
    public static class PopularProduct {

        @Schema(description = "상품 목록 정보")
        private List<ProductDTO> products;
    }
}
