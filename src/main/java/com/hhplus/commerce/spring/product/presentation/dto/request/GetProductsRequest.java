package com.hhplus.commerce.spring.product.presentation.dto.request;

import com.hhplus.commerce.spring.product.domain.dto.query.ProductQuery;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "GetProductsRequest", description = "상품 목록 조회 요청 파라미터")
public record GetProductsRequest (
    @Schema(description = "상품명", example = "보스 QC")
    String productName,
    @Schema(title = "요청할 페이지 번호", description = "마지막으로 조회된 상품의 ID. \n 첫 요청 시에는 null 또는 0 입력", example = "21")
    Integer page,
    @Schema(title = "한 페이지에 보여줄 데이터의 개수", description = "가져올 아이템 개수 \n - default 10", example = "10")
    Integer size
    ){

    public ProductQuery.SummeryList toQuery() {
        return ProductQuery.SummeryList.of(productName, page, size);
    }
}
