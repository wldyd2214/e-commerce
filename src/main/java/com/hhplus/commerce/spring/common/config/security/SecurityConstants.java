package com.hhplus.commerce.spring.common.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpMethod;

public class SecurityConstants {

    public static final Map<HttpMethod, String[]> API_PATH_WHITE_LIST = new HashMap<>();

    static {
        API_PATH_WHITE_LIST.put(HttpMethod.POST, new String[]{ "/users" });
    }
}
