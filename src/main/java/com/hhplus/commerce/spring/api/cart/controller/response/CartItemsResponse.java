package com.hhplus.commerce.spring.api.cart.controller.response;

import com.hhplus.commerce.spring.api.cart.controller.dto.CartItemDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemsResponse {

    @Schema(description = "장바구니 목록 정보")
    private List<CartItemDTO> cartItems;
}
