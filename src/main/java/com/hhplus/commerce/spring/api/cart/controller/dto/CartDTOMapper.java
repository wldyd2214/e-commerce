package com.hhplus.commerce.spring.api.cart.controller.dto;

import com.hhplus.commerce.spring.api.cart.controller.request.CartItemRegisterRequest;
import com.hhplus.commerce.spring.api.cart.controller.request.CartItemsRegisterRequest;
import com.hhplus.commerce.spring.api.cart.controller.response.CartItemsResponse;
import com.hhplus.commerce.spring.api.product.controller.dto.ProductDTO;
import com.hhplus.commerce.spring.api.product.controller.dto.ProductDTOMapper;
import com.hhplus.commerce.spring.api.cart.service.request.CartItemRegister;
import com.hhplus.commerce.spring.api.cart.service.request.CartRegisterRequest;
import com.hhplus.commerce.spring.api.cart.model.CartItem;
import com.hhplus.commerce.spring.api.product.model.Product;

import java.util.List;
import java.util.stream.Collectors;

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

    public static CartItemsResponse toCartItemsResponse(List<CartItem> cartItems) {
        return CartItemsResponse.builder()
                                .cartItems(cartItems.stream().map(CartDTOMapper::toCartItemDTO).collect(Collectors.toList()))
                                .build();
    }

    public static CartItemDTO toCartItemDTO(CartItem cartItem) {
        return CartItemDTO.builder()
                          .id(cartItem.getId())
                          .product(toProductDTO(cartItem.getProduct()))
                          .orderCount(cartItem.getCartItemProductCount())
                          .build();
    }

    public static ProductDTO toProductDTO(Product product) {
        return ProductDTO.builder()
                         .id(product.getId())
                         .name(product.getProductName())
                         .consumerPrice(product.getProductPrice())
                         .stockCount(product.getProductCount())
                         .build();
    }

    public static CartRegisterRequest toCartRegisterRequest(CartItemsRegisterRequest cartItemsRegisterRequest) {
        return CartRegisterRequest.builder()
                                  .cartItems(cartItemsRegisterRequest.getCartItems()
                                                                     .stream()
                                                                     .map(CartDTOMapper::toCartItemRegister)
                                                                     .collect(Collectors.toList()))
                                  .build();
    }

    public static CartItemRegister toCartItemRegister(CartItemRegisterRequest cartItemRegisterRequest) {
        return CartItemRegister.builder()
                               .productId(cartItemRegisterRequest.getProductId())
                               .orderCount(cartItemRegisterRequest.getOrderCount())
                               .build();
    }
}
