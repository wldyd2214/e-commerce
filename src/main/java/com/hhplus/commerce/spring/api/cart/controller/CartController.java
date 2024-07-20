package com.hhplus.commerce.spring.api.cart.controller;

import com.hhplus.commerce.spring.api.ApiResponse;
import com.hhplus.commerce.spring.api.cart.controller.dto.CartDTOMapper;
import com.hhplus.commerce.spring.api.cart.controller.request.CartItemRemoveRequest;
import com.hhplus.commerce.spring.api.cart.controller.request.CartItemsRegisterRequest;
import com.hhplus.commerce.spring.api.cart.controller.response.CartResponse;
import com.hhplus.commerce.spring.api.cart.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/carts")
public class CartController {

    private final CartService cartService;

    @Operation(
            summary = "장바구니 목록 조회 API",
            description = "사용자의 장바구니 목록 정보를 반환 합니다."
    )
    @GetMapping(value = "/{userId}")
    public ApiResponse<CartResponse> getCart(@PathVariable @Valid @Positive Long userId) {
        return ApiResponse.ok(CartDTOMapper.toCartResponse(cartService.getCart(userId)));
    }

    @Operation(
            summary = "장바구니 목록 추가 API",
            description = "사용자의 장바구니 목록 정보를 반환 합니다."
    )
    @PostMapping(value = "/{userId}/item")
    public ApiResponse<CartResponse> addCartItems(
            @PathVariable @Valid @Positive Long userId,
            @RequestBody CartItemsRegisterRequest cartItemsRegisterRequest) {
        return ApiResponse.ok(CartDTOMapper.toCartResponse(
                cartService.addCart(userId, CartDTOMapper.toCartRegisterRequest(cartItemsRegisterRequest))));
    }

    @Operation(
            summary = "장바구니 목록 삭제 API",
            description = "사용자의 장바구니 목록 정보를 삭제 합니다."
    )
    @DeleteMapping(value = "/{userId}/item")
    public ApiResponse<CartResponse> removeCartItems(
            @PathVariable @Valid @Positive Long userId,
            @RequestBody CartItemRemoveRequest cartItemRemoveRequest
    ) {
        return ApiResponse.ok(CartDTOMapper.toCartResponse(
                cartService.removeCartItems(userId, cartItemRemoveRequest.getCartItemIds())));
    }
}
