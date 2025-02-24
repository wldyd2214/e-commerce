package com.hhplus.commerce.spring.domain.user.service;

import com.hhplus.commerce.spring.domain.user.dto.UserCommand;
import com.hhplus.commerce.spring.domain.user.dto.UserInfo;
import com.hhplus.commerce.spring.domain.user.entity.User;
import com.hhplus.commerce.spring.domain.user.mapper.UserMapper;
import com.hhplus.commerce.spring.domain.user.repository.UserQueryRepository;
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

    private final UserQueryRepository userQueryRepository;

    private final UserMapper userMapper;

    private User findUserById(Long userId) {
        return userQueryRepository.findById(userId)
                                  .orElseThrow(() -> new CustomBadRequestException(BadRequestErrorCode.USER_BAD_REQUEST));
    }

    public UserInfo findUserInfoById(Long userId) {
        User entity = findUserById(userId);
        return userMapper.toUserInfo(entity);
    }

    @Transactional
    public UserInfo chargeUserPoints(UserCommand.PointCharge command) {
        /**
         * Q. 코드는 객체 지향적이지만 책임이 두개가 되어 단일 책임의 원칙을 위반한다?
         * 1. 유저 조회
         * 2. 포인트 충전
         *
         * A. UserService.chargeUserPoints() 는 '사용자 포인트 충전' 의 책임을 가지는 것
         * 이 과정에서 findUserById()를 통해 UserEntity를 받아 사용하게 되는 현상은 자연스러움
         * 도메인 객체 간 협력이 일어나는 장소가 Service 이다.
         * 물론 포인트 충전이 아닌 다른 속성을 변경하는 필요 이상의 접근이 경우 책임이 위반되었다고 불 수도 있다.
         */
        User entity = findUserById(command.getUserId());

        // 비관적 락 적용
        try {
            entity.chargePoint(command.getChargePoint());
        } catch (OptimisticLockingFailureException e) {
            throw new CustomConflictException(ConflictErrorCode.USER_POINT_CHARGE_CONFLICT);
        }

        return userMapper.toUserInfo(entity);
    }

    /**
     * 1. 사용자 포인트 차감시 동시성 문제를 위해 낙관적 락 적용
     */
    public void useRewardPoints(UserCommand.RewardPoint command) {
        // 사용자 포인트 낙관적 락 적용
//        User user = userQueryRepository.findByIdWithLock(command.getUserId())
//                                  .orElseThrow(() -> new CustomBadRequestException(
//                                          BadRequestErrorCode.USER_BAD_REQUEST));

//        try {
//            user.deductUserPoint(totalPrice);
//        } catch (OptimisticLockException e) {
//            throw new CustomConflictException(ConflictErrorCode.USER_POINT_DEDUCTION_CONFLICT);
//        }
    }
}
