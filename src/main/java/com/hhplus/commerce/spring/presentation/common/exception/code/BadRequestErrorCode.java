package com.hhplus.commerce.spring.presentation.common.exception.code;

import com.hhplus.commerce.spring.presentation.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BadRequestErrorCode implements ErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "40000000", "잘못된 요청 파라미터"),

    // 잘못된 요청 관련
    POSITIVE_POINT_BAD_REQUEST(HttpStatus.BAD_REQUEST, "40000100", "충전 액수가 0보다 작음"),

    // 사용자 관련
    USER_BAD_REQUEST(HttpStatus.BAD_REQUEST, "40000200", "미존재 사용자 정보"),
    USER_INSUFFICIENT_BALANCE_BAD_REQUEST(HttpStatus.BAD_REQUEST, "40000201", "사용자 포인트 부족"),

    // 장바구니 관련
    CART_BAD_REQUEST(HttpStatus.BAD_REQUEST, "40000300", "미존재 장바구니 정보"),
    CART_ITEM_BAD_REQUEST(HttpStatus.BAD_REQUEST, "40000301", "미존재 장바구니 정보"),

    // 상품 관련
    PRODUCT_BAD_REQUEST(HttpStatus.BAD_REQUEST, "40000400", "미존재 상품 정보"),
    PRODUCT_STOCK_BAD_REQUEST(HttpStatus.BAD_REQUEST, "40000401", "상품 재고 부족");


    private HttpStatus httpStatus;
    private String code;
    private String message;
}
