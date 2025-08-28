package com.hhplus.commerce.spring.user.domain.service;

import com.hhplus.commerce.spring.user.domain.command.UserCommand;
import com.hhplus.commerce.spring.user.domain.dto.UserTokenInfo;

public interface AuthService {
    // 인증 토큰 생성
    UserTokenInfo createAuthToken(UserCommand.Login command);
}
