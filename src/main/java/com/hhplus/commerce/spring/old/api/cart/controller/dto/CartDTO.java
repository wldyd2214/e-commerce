package com.hhplus.commerce.spring.old.api.cart.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDTO {

    @Schema(description = "장바구니 아이디")
    private Long id;

    @Schema(description = "장바구니 목록 정보")
    private List<CartItemDTO> cartItems;
}
