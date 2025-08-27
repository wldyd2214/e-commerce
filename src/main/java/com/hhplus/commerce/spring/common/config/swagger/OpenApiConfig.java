package com.hhplus.commerce.spring.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

@Configuration
public class OpenApiConfig {

    @Value("${swagger.server-url}")
    private String serverUrl;

    @Value("${swagger.server-description}")
    private String serverDescription;

    private final String TOKEN_TITLE = "Bearer Token";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .info(createInfo())
            .servers(Collections.singletonList(new Server().url(serverUrl).description(serverDescription)))
            .components(new Components().addSecuritySchemes(TOKEN_TITLE, getSecurityScheme()))
            .addSecurityItem(getSecurityItem())
            .externalDocs(new ExternalDocumentation().description("OpenAPI 명세서"));
    }

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
//            .group("public-apis")
            .pathsToMatch("/**")
            .pathsToExclude("/orders/webhooks/**")
            .build();
    }

    private Info createInfo() {
        return new Info()
            .title("e-commerce API")
            .description("e-commerce API 문서")
            .version("v1.0.0");
    }

    private SecurityScheme getSecurityScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
            .in(SecurityScheme.In.HEADER)
            .name("Authorization")
            .scheme("bearer")
            .bearerFormat("JWT")
            .description("""
                    로그인 API를 통해 발급받은 accessToken을 입력해주세요.
            """);
    }

    private List<SecurityRequirement> getSecurityRequirement() {
        List<SecurityRequirement> requirements = new ArrayList<>();
        requirements.add(new SecurityRequirement().addList(TOKEN_TITLE));
        return requirements;
    }

    private SecurityRequirement getSecurityItem() {
        return new SecurityRequirement().addList(TOKEN_TITLE);
    }

    @Bean
    public OperationCustomizer customize() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            DisableSwaggerSecurity methodAnnotation =
                handlerMethod.getMethodAnnotation(DisableSwaggerSecurity.class);
            // DisableSecurity 어노테이션 없을시 스웨거 시큐리티 설정 적용
            if (methodAnnotation != null) {
                operation.setSecurity(Collections.emptyList());
            }
            return operation;
        };
    }
}
