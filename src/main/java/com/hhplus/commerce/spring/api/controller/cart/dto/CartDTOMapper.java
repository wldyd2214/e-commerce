package com.hhplus.commerce.spring.api.controller.cart.dto;

import com.hhplus.commerce.spring.api.controller.cart.response.CartItemsResponse;
import com.hhplus.commerce.spring.api.controller.product.dto.ProductDTOMapper;

import java.util.List;

public class CartDTOMapper {

    public static CartItemsResponse createDummyCartsDTO() {
        return CartItemsResponse.builder()
                                .cartItems(
                                   List.of(
                                           carteDummyCartDTO(1L, "아더에러 모자"),
                                           carteDummyCartDTO(2L, "아더에러 바지"))
                           ).build();
    }

    public static CartItemDTO carteDummyCartDTO(Long id, String ProductName) {
        return CartItemDTO.builder()
                          .id(id)
                          .product(ProductDTOMapper.createProductDTO(id, ProductName, 160000, 10))
                          .orderCount(1)
                          .build();
    }
}
