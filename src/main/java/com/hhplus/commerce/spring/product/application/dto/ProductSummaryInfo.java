package com.hhplus.commerce.spring.product.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ProductSummaryInfo {

    @Schema(description = "상품 유니크키", example = "101")
    @NotNull
    private Long id;

    @Schema(description = "상품명", example = "보스 QC 울트라 헤드폰")
    @NotNull
    private String name;

    @Schema(description = "상품 금액", example = "499000")
    @NotNull
    private BigDecimal price;

    @Schema(description = "상품 상태")
    @NotNull
    private String status;

    @Schema(description = "상품 카테고리 유니크키")
    @NotNull
    private Long categoryId;
}
