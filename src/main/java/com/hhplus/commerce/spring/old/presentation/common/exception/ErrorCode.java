package com.hhplus.commerce.spring.old.presentation.common.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String getCode();
    HttpStatus getHttpStatus();
    String getMessage();
}
