package com.hhplus.commerce.spring.api.controller.cart;

import com.hhplus.commerce.spring.api.ApiResponse;
import com.hhplus.commerce.spring.api.controller.cart.dto.CartDTOMapper;
import com.hhplus.commerce.spring.api.controller.cart.request.CartItemRemoveRequest;
import com.hhplus.commerce.spring.api.controller.cart.request.CartItemsRegisterRequest;
import com.hhplus.commerce.spring.api.controller.cart.response.CartItemsResponse;
import com.hhplus.commerce.spring.api.service.cart.CartService;
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
    @GetMapping(value = "/{userId}/item")
    public ApiResponse<CartItemsResponse> getCartItems(@PathVariable @Valid @Positive Long userId) {
        return ApiResponse.ok(CartDTOMapper.toCartItemsResponse(cartService.getCartItems(userId)));
    }

    @Operation(
            summary = "장바구니 목록 추가 API",
            description = "사용자의 장바구니 목록 정보를 반환 합니다."
    )
    @PostMapping(value = "/{userId}/item")
    public ApiResponse<CartItemsResponse> addCartItems(
            @PathVariable @Valid @Positive Long userId,
            @RequestBody CartItemsRegisterRequest cartItemsRegisterRequest) {
        return ApiResponse.ok(CartDTOMapper.toCartItemsResponse(
                cartService.addCartItems(userId, CartDTOMapper.toCartRegisterRequest(cartItemsRegisterRequest))));
    }

    @Operation(
            summary = "장바구니 목록 삭제 API",
            description = "사용자의 장바구니 목록 정보를 삭제 합니다."
    )
    @DeleteMapping(value = "/{userId}/item")
    public ApiResponse<CartItemsResponse> deleteCartItems(
            @PathVariable @Valid @Positive Long userId,
            @RequestBody CartItemRemoveRequest cartItemRemoveRequest
    ) {
        cartService.removeCartItems(cartItemRemoveRequest.getCartItemIds());
        return ApiResponse.ok(null);
    }
}
