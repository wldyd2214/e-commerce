package com.hhplus.commerce.spring.api.common.presentation.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum NotFoundErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "40400001", "사용자 정보 미존재"),
    CART_NOT_FOUND(HttpStatus.NOT_FOUND, "40400002", "장바구니 정보 미존재"),
    CART_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "40400003", "장바구니 목록 정보 미존재"),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "40400004", "상품 정보 미존재");

    private HttpStatus httpStatus;
    private String code;
    private String message;
}
