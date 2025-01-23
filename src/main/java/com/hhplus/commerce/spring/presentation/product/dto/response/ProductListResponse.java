package com.hhplus.commerce.spring.presentation.product.dto.response;

import com.hhplus.commerce.spring.presentation.product.dto.ProductDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProductListResponse {
    @Schema(description = "전체 개수")
    private Integer totalCount;
    @Schema(description = "현재 페이지")
    private Integer currentPage;
    @Schema(description = "상품 목록 정보")
    private List<ProductDTO> products;
}
