package com.hhplus.commerce.spring.old.api.cart.controller.response;

import com.hhplus.commerce.spring.old.api.cart.controller.dto.CartDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {

    @Schema(description = "장바구니 정보")
    private CartDTO cart;
}
