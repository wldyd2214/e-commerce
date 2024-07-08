package com.hhplus.commerce.spring.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "e-커머스 API 명세서",
        description = "e-커머스 API 명세서",
        version = "v1"))
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    // 예시) http://localhost:8080/swagger-ui/index.html
}
