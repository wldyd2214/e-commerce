package com.hhplus.commerce.spring.api.controller.cart.request;

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

    private List<Long> cartItemIds;
}
