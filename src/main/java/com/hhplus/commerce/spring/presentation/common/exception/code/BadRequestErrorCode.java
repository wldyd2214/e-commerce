package com.hhplus.commerce.spring.presentation.common.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BadRequestErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "40000000", "잘못된 요청 파라미터"),
    USER_BAD_REQUEST(HttpStatus.BAD_REQUEST, "40000100", "미존재 사용자 정보"),
    CART_BAD_REQUEST(HttpStatus.BAD_REQUEST, "40000200", "미존재 장바구니 정보"),
    CART_ITEM_BAD_REQUEST(HttpStatus.BAD_REQUEST, "40000201", "미존재 장바구니 정보"),
    PRODUCT_BAD_REQUEST(HttpStatus.BAD_REQUEST, "40000300", "미존재 상품 정보"),
    PRODUCT_STOCK_BAD_REQUEST(HttpStatus.BAD_REQUEST, "40000301", "상품 재고 부족"),
    USER_POINT_BAD_REQUEST(HttpStatus.BAD_REQUEST, "40000400", "사용자 포인트 부족");

    private HttpStatus httpStatus;
    private String code;
    private String message;
}
