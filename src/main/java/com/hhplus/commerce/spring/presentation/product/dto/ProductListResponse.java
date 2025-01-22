package com.hhplus.commerce.spring.presentation.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProductListResponse {

    @Schema(description = "상품 목록 정보")
    private List<ProductDTO> products;
}
