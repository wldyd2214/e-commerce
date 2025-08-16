package com.hhplus.commerce.spring.old.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
            .title("이커머스 서비스")
            .description("상품과 서비스를 사고 파는 전자상거래 서비스 입니다.")
            .version("1.0");
    }


    // TODO: AuthToken 사용시 오픈 예정
//    private final String TOKEN_TITLE = "Bearer Token";

//    @Bean
//    public OpenAPI openAPI() {
//        return new OpenAPI().components(
//                new Components().addSecuritySchemes(TOKEN_TITLE, getSecurityScheme())
//            )
//            .info(apiInfo())
//            .security(getSecurityRequirement())
//            .addSecurityItem(getSecurityItem());
//    }

//    private SecurityScheme getSecurityScheme() {
//        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
//            .in(SecurityScheme.In.HEADER)
//            .name("Authorization")
//            .scheme("bearer")
//            .bearerFormat("JWT")
//            .description("""
//                                           로그인 API를 통해 발급받은 accessToken을 입력해주세요.
//                                       """);
//    }
//
//    private List<SecurityRequirement> getSecurityRequirement() {
//        List<SecurityRequirement> requirements = new ArrayList<>();
//        requirements.add(new SecurityRequirement().addList(TOKEN_TITLE));
//        return requirements;
//    }
//
//    private SecurityRequirement getSecurityItem() {
//        return new SecurityRequirement().addList(TOKEN_TITLE);
//    }
}
