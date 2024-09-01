package com.hhplus.commerce.spring.old.api.cart.controller.dto;

import com.hhplus.commerce.spring.old.api.cart.controller.request.CartItemRegisterRequest;
import com.hhplus.commerce.spring.old.api.cart.controller.request.CartItemsRegisterRequest;
import com.hhplus.commerce.spring.old.api.cart.controller.response.CartResponse;
import com.hhplus.commerce.spring.old.api.cart.model.Cart;
import com.hhplus.commerce.spring.old.api.cart.service.response.CartItemProductRes;
import com.hhplus.commerce.spring.old.api.cart.service.response.CartItemServiceRes;
import com.hhplus.commerce.spring.old.api.cart.service.response.CartServiceRes;
import com.hhplus.commerce.spring.old.api.product.controller.dto.ProductDTO;
import com.hhplus.commerce.spring.old.api.cart.service.request.CartItemRegister;
import com.hhplus.commerce.spring.old.api.cart.service.request.CartRegisterRequest;
import com.hhplus.commerce.spring.old.api.cart.model.CartItem;
import com.hhplus.commerce.spring.old.api.product.model.Product;

import java.util.stream.Collectors;

public class CartDTOMapper {

    public static CartResponse toCartResponse(Cart cart) {
        return CartResponse.builder()
                           .cart(toCartDTO(cart))
                           .build();
    }

    public static CartDTO toCartDTO(Cart cart) {
        return CartDTO.builder()
                      .id(cart.getId())
                      .cartItems(cart.getCartItems().stream().map(CartDTOMapper::toCartItemDTO).collect(Collectors.toList()))
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

    public static CartRegisterRequest toCartRegisterRequest(
        CartItemsRegisterRequest cartItemsRegisterRequest) {
        return CartRegisterRequest.builder()
                                  .cartItems(cartItemsRegisterRequest.getCartItems()
                                                                     .stream()
                                                                     .map(CartDTOMapper::toCartItemRegister)
                                                                     .collect(Collectors.toList()))
                                  .build();
    }

    public static CartItemRegister toCartItemRegister(
        CartItemRegisterRequest cartItemRegisterRequest) {
        return CartItemRegister.builder()
                               .productId(cartItemRegisterRequest.getProductId())
                               .orderCount(cartItemRegisterRequest.getOrderCount())
                               .build();
    }

    public static CartResponse toCartResponse(CartServiceRes cartServiceRes){
        return CartResponse.builder()
                           .cart(toCartDTO(cartServiceRes))
                           .build();
    }

    public static CartDTO toCartDTO(CartServiceRes cartServiceRes) {
        return CartDTO.builder()
                      .id(cartServiceRes.getId())
                      .cartItems(cartServiceRes.getCartItems().stream().map(CartDTOMapper::toCartItemDTO).collect(Collectors.toList()))
                      .build();
    }

    public static CartItemDTO toCartItemDTO(CartItemServiceRes cartItemServiceRes) {
        return CartItemDTO.builder()
                          .id(cartItemServiceRes.getId())
                          .product(toProductDTO(cartItemServiceRes.getProduct()))
                          .orderCount(cartItemServiceRes.getAddProductCount())
                          .build();
    }

    public static ProductDTO toProductDTO(CartItemProductRes cartItemProductRes) {
        return ProductDTO.builder()
                         .id(cartItemProductRes.getId())
                         .name(cartItemProductRes.getName())
                         .consumerPrice(cartItemProductRes.getPrice())
                         .stockCount(cartItemProductRes.getCount())
                         .build();
    }
}
