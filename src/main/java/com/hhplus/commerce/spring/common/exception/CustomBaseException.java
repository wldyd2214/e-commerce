package com.hhplus.commerce.spring.common.exception;

import com.hhplus.commerce.spring.common.exception.code.BaseErrorCode;

public abstract class CustomBaseException extends RuntimeException {

    public CustomBaseException(String message) {
        super(message);
    }

    public abstract BaseErrorCode getErrorCode();
}
