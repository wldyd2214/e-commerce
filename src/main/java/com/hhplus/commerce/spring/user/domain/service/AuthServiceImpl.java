package com.hhplus.commerce.spring.user.domain.service;

import com.hhplus.commerce.spring.user.domain.command.UserCommand;
import com.hhplus.commerce.spring.user.domain.dto.UserTokenInfo;
import com.hhplus.commerce.spring.user.infrastructure.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    /**
     * 인증 토큰 생성
     * @param command (UserCommand.Login)
     * @return
     */
    @Override
    public UserTokenInfo createAuthToken(UserCommand.Login command) {
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(command.getEmail(), command.getPassword());

        // UserDetailsServiceImpl.loadUserByUsername() 수행
        Authentication authentication = authenticationManager.authenticate(authRequest);

        // 토큰 생성
        String jwtToken = jwtProvider.generateAccessToken(authentication);

        // 응답 정보 리턴
        return UserTokenInfo.of(jwtToken, jwtProvider.getAccessTokenExpireSec());
    }
}
