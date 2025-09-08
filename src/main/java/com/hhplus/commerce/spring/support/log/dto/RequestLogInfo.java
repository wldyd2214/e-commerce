package com.hhplus.commerce.spring.support.log.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.commerce.spring.common.util.JsonUtil;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.util.ContentCachingRequestWrapper;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class RequestLogInfo {
    private String method;
    private String path;
    private String query;
    private JsonNode body;

    public static RequestLogInfo create(ContentCachingRequestWrapper requestWrapper, ObjectMapper objectMapper) {
        return RequestLogInfo.builder()
            .method(requestWrapper.getMethod())
            .path(requestWrapper.getRequestURI())
            .query(requestWrapper.getQueryString())
            .body(JsonUtil.getJsonBody(requestWrapper.getContentAsByteArray(), objectMapper))
            .build();
    }
}
