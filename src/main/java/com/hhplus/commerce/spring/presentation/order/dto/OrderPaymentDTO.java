package com.hhplus.commerce.spring.presentation.order.dto;

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
    @Positive(message = "상품 아이디는 양수여야 합니다.")
    private Long productId;

    @Schema(description = "상품 주문 갯수", example = "2")
    @Positive(message = "상품 주문 갯수는 양수여야 합니다.")
    private Integer orderCount;
}
