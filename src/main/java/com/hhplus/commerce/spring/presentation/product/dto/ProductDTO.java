package com.hhplus.commerce.spring.presentation.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDTO {

    @Schema(description = "상품 아이디", example = "1")
    private Long id;
    @Schema(description = "상품 이름", example = "Blue Easter Egg Game t-shirt 01")
    private String name;
    @Schema(description = "상품 가격", example = "149000")
    private Integer consumerPrice;
    @Schema(description = "상품 재고", example = "49")
    private Integer stockCount;
}
