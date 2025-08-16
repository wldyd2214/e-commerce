package com.hhplus.commerce.spring.user.domain.service;

import com.hhplus.commerce.spring.user.domain.command.UserCommand.ChargePoint;
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
     * @param toCommand
     */
    @Override
    @Transactional
    public UserSummaryInfo chargeUserPoints(ChargePoint toCommand) {
        // 사용자 정보 조회
        User user = getUser(toCommand.getUserId());

        // 포인트 충전
        user.chargePoint(toCommand.getChargePoint());

        // 사용자 포인트 충전 정보 리턴
        return UserSummaryInfo.of(user);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                             .orElseThrow(() -> new IllegalArgumentException("미존재 회원 정보"));
    }
}
