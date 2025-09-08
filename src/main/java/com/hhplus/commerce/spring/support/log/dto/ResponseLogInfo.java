package com.hhplus.commerce.spring.support.log.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.commerce.spring.common.util.JsonUtil;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ResponseLogInfo {
    private int statusCode;
    private JsonNode body;

    public static ResponseLogInfo create(ContentCachingResponseWrapper responseWrapper, ObjectMapper objectMapper) {
        return ResponseLogInfo.builder()
            .statusCode(responseWrapper.getStatus())
            .body(JsonUtil.getJsonBody(responseWrapper.getContentAsByteArray(), objectMapper))
            .build();
    }
}
