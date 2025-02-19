package com.hhplus.commerce.spring.old.api.cart.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemRegisterRequest {

    @Schema(description = "상품 아이디", example = "1")
    private Long productId;
    @Schema(description = "상품 갯수", example = "1")
    private Integer orderCount;
}
