package com.hhplus.commerce.spring.old.presentation.cart;

import com.hhplus.commerce.spring.old.application.cart.CartFacade;
import com.hhplus.commerce.spring.old.application.cart.dto.CartFacadeRequest;
import com.hhplus.commerce.spring.old.domain.cart.CartService;
import com.hhplus.commerce.spring.old.domain.cart.dto.common.CartInfo;
import com.hhplus.commerce.spring.old.domain.cart.dto.request.CartCommand;
import com.hhplus.commerce.spring.old.presentation.cart.dto.CartRequest;
import com.hhplus.commerce.spring.old.presentation.cart.dto.CartResponse;
import com.hhplus.commerce.spring.old.presentation.cart.mapper.CartRequestMapper;
import com.hhplus.commerce.spring.old.presentation.cart.mapper.CartResponseMapper;
import com.hhplus.commerce.spring.common.ApiResponse;
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
    private final CartService cartService;

    // Mapper
    private final CartRequestMapper requestMapper;
    private final CartResponseMapper responseMapper;

    @Operation(
        summary = "장바구니 목록 추가 API",
        description = "사용자의 장바구니 목록 정보를 반환 합니다."
    )
    @PostMapping(value = "/{userId}/item")
    public ApiResponse<CartResponse.AddCartItem> addCartItems(
        @PathVariable @Valid @Positive Long userId,
        @RequestBody CartRequest.AddItem request) {

        // 1. Facade 요청 객체 변환
        CartFacadeRequest.AddItem facadeRequest = requestMapper.toFacadeAddItem(userId, request);

        // 2. 장바구니 목록 추가
        CartInfo cartInfo = cartFacade.processAddCartItems(facadeRequest);

        // 3. Facade 응답 객체 변환
        CartResponse.AddCartItem response = responseMapper.toAddCartIem(cartInfo);

        return ApiResponse.ok(response);
    }

    @Operation(
        summary = "장바구니 목록 조회 API",
        description = "사용자의 장바구니 목록 정보를 반환 합니다."
    )
    @GetMapping(value = "/{userId}")
    public ApiResponse<CartResponse.Cart> getCart(@PathVariable @Valid @Positive Long userId) {

        // 1. 장바구니 목록 조회
        CartInfo cartInfo = cartService.getCartInfoByUserId(userId);

        // 2. 응답 객체 변환
        CartResponse.Cart response = responseMapper.toCart(cartInfo);

        return ApiResponse.ok(response);
    }

    @Operation(
        summary = "장바구니 목록 삭제 API",
        description = "사용자의 장바구니 목록 정보를 삭제 합니다."
    )
    @DeleteMapping(value = "/{userId}/item")
    public ApiResponse<CartResponse.Cart> removeCartItems(
        @PathVariable @Valid @Positive Long userId,
        @RequestBody CartRequest.RemoveItem request
    ) {

        // 1. Service 요청 객체 변환
        CartCommand.RemoveCartItem command = requestMapper.toRemoveCartItem(userId, request.getItemIds());

        // 2. CartItem 제거 요청
        CartInfo cartInfo = cartService.removeCartItems(command);

        // 3. 응답 객체 변환
        CartResponse.Cart response = responseMapper.toCart(cartInfo);

        return ApiResponse.ok(response);
    }
}
