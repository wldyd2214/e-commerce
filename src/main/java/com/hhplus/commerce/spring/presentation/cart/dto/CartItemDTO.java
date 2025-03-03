package com.hhplus.commerce.spring.presentation.cart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CartItemDTO {

    @Schema(description = "상품 아이디", example = "1")
    @NotNull(message = "상품 ID는 필수 값입니다.")
    @Positive(message = "상품 ID는 양수만 가능합니다.")
    private Long productId;

    @Schema(description = "상품 갯수", example = "1")
    @NotNull(message = "주문 개수는 필수 값입니다.")
    @Min(value = 1, message = "주문 개수는 1개 이상이어야 합니다.")
    private Integer orderCount;
}
