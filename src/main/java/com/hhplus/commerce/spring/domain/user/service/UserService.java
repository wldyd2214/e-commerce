package com.hhplus.commerce.spring.domain.user.service;

import com.hhplus.commerce.spring.domain.user.dto.UserCommand;
import com.hhplus.commerce.spring.domain.user.mapper.UserMapper;
import com.hhplus.commerce.spring.domain.user.entity.UserEntity;
import com.hhplus.commerce.spring.domain.user.dto.User;
import com.hhplus.commerce.spring.domain.user.repository.UserRepository;
import com.hhplus.commerce.spring.presentation.common.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.presentation.common.exception.CustomConflictException;
import com.hhplus.commerce.spring.presentation.common.exception.code.BadRequestErrorCode;
import com.hhplus.commerce.spring.presentation.common.exception.code.ConflictErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    private UserEntity findUserEntityById(Long userId) {
        return userRepository.findById(userId)
                             .orElseThrow(() -> new CustomBadRequestException(BadRequestErrorCode.USER_BAD_REQUEST));
    }

    public User findUserById(Long userId) {
        UserEntity entity = findUserEntityById(userId);
        return UserMapper.toUser(entity);
    }

    @Transactional
    public User chargeUserPoints(UserCommand.PointCharge command) {
        /**
         * Q. 코드는 객체 지향적이지만 책임이 두개가 되어 단일 책임의 원칙을 위반한다?
         * 1. 유저 조회
         * 2. 포인트 충전
         *
         * A. UserService.chargeUserPoints() 는 '사용자 포인트 충전' 의 책임을 가지는 것
         * 이 과정에서 findUserEntityById()를 통해 UserEntity를 받아 사용하게 되는 현상은 자연스러움
         * 도메인 객체 간 협력이 일어나는 장소가 Service 이다.
         * 물론 포인트 충전이 아닌 다른 속성을 변경하는 필요 이상의 접근이 경우 책임이 위반되었다고 불 수도 있다.
         */
        UserEntity entity =
                userRepository.findById(command.getUserId())
                              .orElseThrow(() -> new CustomBadRequestException(BadRequestErrorCode.USER_BAD_REQUEST));
        try {
            entity.chargePoint(command.getChargePoint());
        } catch (OptimisticLockingFailureException e) {
            throw new CustomConflictException(ConflictErrorCode.USER_POINT_CHARGE_CONFLICT);
        }

        return UserMapper.toUser(entity);
    }
}
