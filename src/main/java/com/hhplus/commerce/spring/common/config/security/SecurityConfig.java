package com.hhplus.commerce.spring.common.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

@Configuration
public class SecurityConfig {

    private static final String[] SWAGGER_PATH_WHITE_LIST = { "/actuator/health", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**" };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults()) // CORS 허용 활성화
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(
                auth -> auth
                    .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()

                    // swagger 허용
                    .requestMatchers(SWAGGER_PATH_WHITE_LIST).permitAll()

                    // 인증 불필요 API
                    .requestMatchers(HttpMethod.POST, "/users").permitAll()

                    // 그 외에는 인증 필요
                    .anyRequest().authenticated()
            );
//            .addFilterBefore(new JwtAuthenticationFilter(jwtUtil, jwtProperties, objectMapper, redisTemplate), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
