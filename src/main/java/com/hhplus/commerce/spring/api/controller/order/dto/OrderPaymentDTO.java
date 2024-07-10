package com.hhplus.commerce.spring.api.controller.order.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPaymentDTO {

    @Schema(description = "상품 아이디", example = "1")
    @Positive
    private Long productId;

    @Schema(description = "상품 주문 갯수", example = "2")
    @Positive
    private Integer orderCount;
}
