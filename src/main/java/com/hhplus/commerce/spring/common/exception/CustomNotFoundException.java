package com.hhplus.commerce.spring.common.exception;

import com.hhplus.commerce.spring.common.exception.code.NotFoundErrorCode;
import lombok.Getter;

@Getter
public class CustomNotFoundException extends CustomBaseException {

    private final NotFoundErrorCode errorCode;

    public CustomNotFoundException(NotFoundErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
