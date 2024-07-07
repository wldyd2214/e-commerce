package com.hhplus.commerce.spring.api.controller.cart.dto;

import com.hhplus.commerce.spring.api.controller.product.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDTO {

    private Long id;
    private ProductDTO product;
    private Integer orderCount;
}
