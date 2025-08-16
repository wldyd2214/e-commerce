package com.hhplus.commerce.spring.user.domain.service;

import com.hhplus.commerce.spring.user.domain.command.UserCommand.ChargePoint;
import com.hhplus.commerce.spring.user.domain.dto.UserSummaryInfo;
import com.hhplus.commerce.spring.user.domain.entity.User;
import com.hhplus.commerce.spring.user.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     * 사용자 포인트 충전
     * @param toCommand
     */
    @Override
    public UserSummaryInfo chargeUserPoints(ChargePoint toCommand) {
        User user = userRepository.findById(toCommand.getUserId()).orElseThrow(() -> new IllegalArgumentException("미존재 회원 정보"));
        user.chargePoint(toCommand.getChargePoint());

        return UserSummaryInfo.of(user);
    }
}
