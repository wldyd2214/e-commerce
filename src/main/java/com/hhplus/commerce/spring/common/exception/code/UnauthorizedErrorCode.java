package com.hhplus.commerce.spring.common.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UnauthorizedErrorCode implements BaseErrorCode {
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "40100000", "권한 없음"),

    /** 토큰이 변조되었거나 서명 불일치 그외 토큰 에러 */
    UNAUTHORIZED_TOKEN_ERROR(HttpStatus.UNAUTHORIZED, "40100001", "유효하지 않은 토큰"),
    UNAUTHORIZED_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "40100002", "토큰 만료");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
