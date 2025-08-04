package com.hhplus.commerce.spring.old.presentation.common.exception;

import com.hhplus.commerce.spring.old.presentation.common.exception.code.ConflictErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomConflictException extends RuntimeException {
    private ConflictErrorCode errorCode;

    public CustomConflictException(ConflictErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
