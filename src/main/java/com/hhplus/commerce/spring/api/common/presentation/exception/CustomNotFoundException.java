package com.hhplus.commerce.spring.api.common.presentation.exception;

import com.hhplus.commerce.spring.api.common.presentation.exception.code.NotFoundErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomNotFoundException extends RuntimeException {
    private NotFoundErrorCode errorCode;

    public CustomNotFoundException(NotFoundErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
