package com.hhplus.commerce.spring.user.infrastructure.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Collections;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtProvider implements InitializingBean {

    private static final String TOKEN_PREFIX = "Bearer ";

    private Key key;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.access-token-expire-sec}")
    private long accessTokenExpireSec;

    @Override
    public void afterPropertiesSet() {
        // 빈이 주입을 받은 후 secretKey 값을 Base64 Decode 해서 Key 변수에 할당
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * AccessToken 생성
     *
     * @param authentication 인증객체
     * @return AccessToken
     */
    public String generateAccessToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // 생성일 & 만료일
        Date now = new Date();
        Date expiresIn = new Date(now.getTime() + accessTokenExpireSec * 1000L);

        return Jwts.builder()
            .setSubject(authentication.getName())
            .claim("user",  userDetails)
            .signWith(key, SignatureAlgorithm.HS256)
            .setIssuedAt(now)
            .setExpiration(expiresIn)
            .compact();
    }

    public long getAccessTokenExpireSec() {
        return accessTokenExpireSec;
    }

    /**
     * 헤더에서 토큰 정보를 추출하여 리턴한다.
     *
     * @param request HTTP 요청
     * @return 토큰
     */
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.replace(TOKEN_PREFIX, Strings.EMPTY);
        }

        return null;
    }

    /**
     * 토큰 검증
     *
     * @return {@code true}: 검증성공 {@code false}: 검증실패
     */
    public boolean isValidateToken(String token) {
        try {
            getTokenClaims(token);
        } catch (SecurityException e) {
            log.info("Invalid JWT signature. {}", e.getMessage());
            throw e;
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT. {}", e.getMessage());
            throw e;
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT. {}", e.getMessage());
            throw e;
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT. {}", e.getMessage());
            throw e;
        } catch (IllegalArgumentException e) {
            log.info("JWT compact of handler are invalid. {}", e.getMessage());
            throw e;
        }
        return true;
    }

    /**
     * 토큰 정보를 이용해 Claims 객체를 리턴하는 메서드
     *
     * @param token 토큰
     * @return Claims
     */
    private Claims getTokenClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    /**
     * 토큰 정보를 이용해서 Authentication 객체를 생성하여 리턴한다.
     *
     * @param token 토큰
     * @return Authentication
     */
    public Authentication getAuthentication(String token) {
        Claims claims = getTokenClaims(token);

        // claims가 null인 경우 예외 처리
        if (claims == null) {
            throw new IllegalArgumentException("Invalid token: claims are null");
        }

        Long key = Long.valueOf(claims.get("user").toString());

        UserDetailsImpl principal = UserDetailsImpl.create(key, claims.getSubject());
        return new UsernamePasswordAuthenticationToken(principal, token, Collections.emptyList());
    }
}
