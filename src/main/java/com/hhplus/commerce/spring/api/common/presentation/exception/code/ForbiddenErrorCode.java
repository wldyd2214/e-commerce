package com.hhplus.commerce.spring.api.common.presentation.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ForbiddenErrorCode {
    USER_FORBIDDEN(HttpStatus.FORBIDDEN, "40300001", "사용자 권한 정보 없음");

    private HttpStatus httpStatus;
    private String code;
    private String message;
}
