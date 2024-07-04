package com.hhplus.commerce.spring.api.controller.cart;

import com.hhplus.commerce.spring.api.ApiResponse;
import com.hhplus.commerce.spring.api.controller.cart.dto.CartDTOMapper;
import com.hhplus.commerce.spring.api.controller.cart.response.CartItemsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/carts")
public class CartController {

    @GetMapping(value = "/item")
    public ApiResponse<CartItemsResponse> getCartItems() {
        return ApiResponse.ok(CartDTOMapper.createDummyCartsDTO());
    }

    @PostMapping(value = "/item")
    public ApiResponse<CartItemsResponse> addCartItems() {
        return ApiResponse.ok(CartDTOMapper.createDummyCartsDTO());
    }

    @DeleteMapping(value = "/item")
    public ApiResponse<CartItemsResponse> deleteCartItems() {
        return ApiResponse.ok(CartDTOMapper.createDummyCartsDTO());
    }
}
