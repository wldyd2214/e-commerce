package com.hhplus.commerce.spring.common.config.security.fliter;

import com.hhplus.commerce.spring.user.infrastructure.jwt.JwtProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        String token = jwtProvider.resolveToken(request);

        try {
            if (StringUtils.hasText(token) && jwtProvider.isValidateToken(token)) {
                // 토큰이 유요할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext 에 저장
                Authentication authentication = jwtProvider.getAuthentication(token);
                // 권한 부여
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (ExpiredJwtException e) {
            log.info("[JwtAuthenticationFilter.doFilterInternal] - 토큰 만료");
//            request.setAttribute(HEADER_KEY, UnauthorizedErrorCode.UNAUTHORIZED_TOKEN_EXPIRED.getCode());
        } catch (SecurityException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            log.info("[JwtAuthenticationFilter.doFilterInternal] - 유효하지 않은 토큰");
//            request.setAttribute(HEADER_KEY, UnauthorizedErrorCode.UNAUTHORIZED_TOKEN_ERROR.getCode());
        } catch (JwtException e) {
            log.info("[JwtAuthenticationFilter.doFilterInternal] - 토큰 오류");
//            request.setAttribute(HEADER_KEY, UnauthorizedErrorCode.UNAUTHORIZED_TOKEN_ERROR.getCode());
        }

        filterChain.doFilter(request, response);
    }
}
