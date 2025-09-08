package com.hhplus.commerce.spring.support.log.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.commerce.spring.support.log.LoggingInterceptor;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL) // null인 필드는 JSON 변환 시 제외
public class LogInfo {

    private long timestamp;
    private long durationMs;
    private String clientIp;
    private String level;
    private HttpLogInfo http;
    private ErrorLog error;

    public static LogInfo create(ContentCachingRequestWrapper request,  ContentCachingResponseWrapper response, ObjectMapper objectMapper) {
        long startTime = (Long) request.getAttribute(LoggingInterceptor.REQUEST_ATTRIBUTE_START_TIME);
        long duration = System.currentTimeMillis() - startTime;

        return LogInfo.builder()
                .timestamp(System.currentTimeMillis())
                .durationMs(duration)
                .clientIp(request.getRemoteAddr())
                .level("INFO")
                .http(HttpLogInfo.create(request, response, objectMapper))
                .build();
    }

    public void updateError(Exception exception) {
        this.level = "ERROR";
        this.error = ErrorLog.builder().message(exception.getMessage()).build();
    }

    @Getter
    @Builder
    public static class ErrorLog {
        private String message;
    }
}
