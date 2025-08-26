package com.hhplus.commerce.spring.user.domain.service;

import com.hhplus.commerce.spring.common.exception.CustomNotFoundException;
import com.hhplus.commerce.spring.common.exception.code.NotFoundErrorCode;
import com.hhplus.commerce.spring.user.domain.command.UserCommand;
import com.hhplus.commerce.spring.user.domain.dto.UserSummaryInfo;
import com.hhplus.commerce.spring.user.domain.entity.User;
import com.hhplus.commerce.spring.user.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * 사용자 포인트 충전
     * @param command
     */
    @Override
    @Transactional
    public UserSummaryInfo chargeUserPoints(UserCommand.ChargePoint command) {
        // 사용자 정보 조회
        User user = getUser(command.getUserId());

        // 포인트 충전
        user.chargePoint(command.getChargePoint());

        // 사용자 포인트 충전 정보 리턴
        return UserSummaryInfo.of(user);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                             .orElseThrow(() -> new CustomNotFoundException(NotFoundErrorCode.USER_NOT_FOUNT));
    }
}
