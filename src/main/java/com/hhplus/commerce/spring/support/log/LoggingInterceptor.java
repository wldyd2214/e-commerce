package com.hhplus.commerce.spring.support.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.commerce.spring.support.log.dto.LogInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoggingInterceptor implements HandlerInterceptor {

    /**
     * LoggingInterceptor에서 API 요청 처리 시간을 측정하기 위해,
     * 요청 시작 시점의 타임스탬프(Long 타입)를 HttpServletRequest attribute에 저장할 때 사용하는 키입니다.
     */
    public static final String REQUEST_ATTRIBUTE_START_TIME = "startTime";

    private final ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute(REQUEST_ATTRIBUTE_START_TIME, System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        final ContentCachingRequestWrapper requestWrapper = (ContentCachingRequestWrapper) request;
        final ContentCachingResponseWrapper responseWrapper = (ContentCachingResponseWrapper) response;

        LogInfo logInfo = LogInfo.create(requestWrapper, responseWrapper, objectMapper);
        if (ex != null) logInfo.updateError(ex);

        log.info(objectMapper.writeValueAsString(logInfo));
    }
}
