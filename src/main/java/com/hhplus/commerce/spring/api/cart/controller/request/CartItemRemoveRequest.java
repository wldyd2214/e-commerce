package com.hhplus.commerce.spring.api.cart.controller.request;

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
public class CartItemRemoveRequest {

    @Schema(description = "장바구니 아이템 아이디", example = "1, 2, 3")
    private List<Long> cartItemIds;
}
