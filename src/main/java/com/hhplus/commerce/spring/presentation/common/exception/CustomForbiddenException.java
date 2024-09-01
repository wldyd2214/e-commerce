package com.hhplus.commerce.spring.presentation.common.exception;

import com.hhplus.commerce.spring.presentation.common.exception.code.ForbiddenErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomForbiddenException extends RuntimeException {
    private ForbiddenErrorCode errorCode;

    public CustomForbiddenException(ForbiddenErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
