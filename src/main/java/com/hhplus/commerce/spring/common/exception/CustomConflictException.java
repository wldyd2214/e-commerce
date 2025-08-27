package com.hhplus.commerce.spring.common.exception;

import com.hhplus.commerce.spring.common.exception.code.ConflictErrorCode;
import java.text.MessageFormat;
import lombok.Getter;

@Getter
public class CustomConflictException extends CustomBaseException {

    private final ConflictErrorCode errorCode;

    public CustomConflictException(ConflictErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CustomConflictException(ConflictErrorCode errorCode, String args) {
        super(MessageFormat.format(errorCode.getMessage(), args));
        this.errorCode = errorCode;
    }
}
