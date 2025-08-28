package com.hhplus.commerce.spring.common.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ForbiddenErrorCode implements BaseErrorCode {
    FORBIDDEN(HttpStatus.FORBIDDEN, "40300000", "권한 없음");

    private HttpStatus httpStatus;
    private String code;
    private String message;
}
