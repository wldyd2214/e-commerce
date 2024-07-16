package com.hhplus.commerce.spring.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@RequiredArgsConstructor
public class LoggingInterceptor implements HandlerInterceptor {
    private final ObjectMapper objectMapper;
}
