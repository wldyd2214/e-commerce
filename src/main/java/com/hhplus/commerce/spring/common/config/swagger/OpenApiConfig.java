package com.hhplus.commerce.spring.common.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.Collections;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!test")
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
            .group("apis")
            .pathsToMatch("/**")
            // 스웨거 문서 제외
//            .pathsToExclude("/orders/webhooks/**")
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

    private SecurityRequirement getSecurityItem() {
        return new SecurityRequirement().addList(TOKEN_TITLE);
    }
}
