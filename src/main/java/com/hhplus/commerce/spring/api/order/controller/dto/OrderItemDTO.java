package com.hhplus.commerce.spring.api.order.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDTO {
    @Schema(description = "주문 목록 아이디", example = "1")
    private Long id;

    @Schema(description = "주문 상품명", example = "아더에러 티셔츠")
    private String orderProductName;

    @Schema(description = "주문 상품 가격", example = "100000")
    private Integer orderProductPrice;

    @Schema(description = "주문 상품 갯수", example = "1")
    private Integer orderProductCount;
}
