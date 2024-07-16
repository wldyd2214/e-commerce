package com.hhplus.commerce.spring.api.controller.cart.dto;

import com.hhplus.commerce.spring.api.controller.product.dto.ProductDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDTO {

    @Schema(description = "장바구니 아이디", example = "1")
    private Long id;
    private ProductDTO product;
    @Schema(description = "장바구니에 담긴 상품의 갯수", example = "1")
    private Integer orderCount;
}
