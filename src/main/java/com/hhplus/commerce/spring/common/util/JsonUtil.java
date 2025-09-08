package com.hhplus.commerce.spring.common.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class JsonUtil {

    public static JsonNode getJsonBody(byte[] content, ObjectMapper objectMapper) {
        try {
            if (content.length == 0) return null;
            return objectMapper.readTree(content);
        } catch (IOException e) {
            // JSON 파싱 실패 시 원본 문자열을 담은 노드로 반환 (혹은 다른 정책 적용)
            return objectMapper.getNodeFactory().textNode("Failed to parse JSON body: " + new String(content));
        }
    }
}
