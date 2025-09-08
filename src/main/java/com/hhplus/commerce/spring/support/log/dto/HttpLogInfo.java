package com.hhplus.commerce.spring.support.log.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class HttpLogInfo {
    private RequestLogInfo request;
    private ResponseLogInfo response;

    public static HttpLogInfo create(
        ContentCachingRequestWrapper requestWrapper,
        ContentCachingResponseWrapper responseWrapper,
        ObjectMapper objectMapper
    ) {
        return HttpLogInfo.builder()
                .request(RequestLogInfo.create(requestWrapper, objectMapper))
                .response(ResponseLogInfo.create(responseWrapper, objectMapper))
                .build();
    }
}
