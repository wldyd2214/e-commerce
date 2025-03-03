package com.hhplus.commerce.spring.presentation.cart;

import com.hhplus.commerce.spring.application.cart.CartFacade;
import com.hhplus.commerce.spring.application.cart.dto.request.CartFacadeRequest;
import com.hhplus.commerce.spring.application.cart.dto.response.CartFacadeResponse;
import com.hhplus.commerce.spring.old.api.cart.controller.dto.CartDTOMapper;
import com.hhplus.commerce.spring.old.api.cart.controller.request.CartItemRemoveRequest;
import com.hhplus.commerce.spring.old.api.cart.controller.request.CartItemsRegisterRequest;
import com.hhplus.commerce.spring.old.api.cart.controller.response.CartResponse;
import com.hhplus.commerce.spring.presentation.cart.dto.request.CartRequest;
import com.hhplus.commerce.spring.presentation.cart.mapper.CartRequestMapper;
import com.hhplus.commerce.spring.presentation.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "장바구니", description = "장바구니 관련 인터페이스를 제공합니다.")
@RequestMapping(value = "/carts")
public class CartController {

    // Facade
    private final CartFacade cartFacade;

    // Mapper
    private final CartRequestMapper requestMapper;

    @Operation(
        summary = "장바구니 목록 추가 API",
        description = "사용자의 장바구니 목록 정보를 반환 합니다."
    )
    @PostMapping(value = "/{userId}/item")
    public ApiResponse<Void> addCartItems(
        @PathVariable @Valid @Positive Long userId,
        @RequestBody CartRequest.AddItem request) {

        // 1. Facade 요청 객체 변환
        CartFacadeRequest.AddItem facadeRequest = requestMapper.toFacadeAddItem(userId, request);

        // 2. 장바구니 목록 추가
        CartFacadeResponse.AddItem facadeResponse = cartFacade.processAddCartItems(facadeRequest);

        // 3. Facade 응답 객체 변환
        return null;

//        return ApiResponse.ok(CartDTOMapper.toCartResponse(
//            cartService.addCart(userId, CartDTOMapper.toCartRegisterRequest(cartItemsRegisterRequest))));
    }

//    @Operation(
//        summary = "장바구니 목록 조회 API",
//        description = "사용자의 장바구니 목록 정보를 반환 합니다."
//    )
//    @GetMapping(value = "/{userId}")
//    public ApiResponse<CartResponse> getCart(@PathVariable @Valid @Positive Long userId) {
//        return ApiResponse.ok(CartDTOMapper.toCartResponse(cartService.getCart(userId)));
//    }
//
//    @Operation(
//        summary = "장바구니 목록 삭제 API",
//        description = "사용자의 장바구니 목록 정보를 삭제 합니다."
//    )
//    @DeleteMapping(value = "/{userId}/item")
//    public ApiResponse<CartResponse> removeCartItems(
//        @PathVariable @Valid @Positive Long userId,
//        @RequestBody CartItemRemoveRequest cartItemRemoveRequest
//    ) {
//        return ApiResponse.ok(CartDTOMapper.toCartResponse(
//            cartService.removeCartItems(userId, cartItemRemoveRequest.getCartItemIds())));
//    }
}
