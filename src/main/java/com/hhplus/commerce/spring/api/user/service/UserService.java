package com.hhplus.commerce.spring.api.user.service;

import com.hhplus.commerce.spring.api.common.presentation.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.api.common.presentation.exception.CustomConflictException;
import com.hhplus.commerce.spring.api.common.presentation.exception.CustomBadGateWayException;
import com.hhplus.commerce.spring.api.order.service.PaymentService;
import com.hhplus.commerce.spring.api.user.model.User;
import com.hhplus.commerce.spring.api.user.repository.UserRepository;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.hhplus.commerce.spring.api.common.presentation.exception.code.BadGateWayErrorCode.PAYMENT_BAD_GATEWAY;
import static com.hhplus.commerce.spring.api.common.presentation.exception.code.BadRequestErrorCode.USER_BAD_REQUEST;
import static com.hhplus.commerce.spring.api.common.presentation.exception.code.ConflictErrorCode.USER_POINT_CHARGE_CONFLICT;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final PaymentService paymentService;

    private final UserRepository userRepository;

    @Transactional
    public User userBalanceCharge(Long userId, int chargePoint) {

        if (chargePoint < 0) throw new IllegalArgumentException("충전 액수는 0보다 커야 합니다.");

        User user = userRepository.findByIdWithLock(userId)
                                  .orElseThrow(() -> new CustomBadRequestException(USER_BAD_REQUEST));

        try {
            user.pointCharge(chargePoint);
        } catch (OptimisticLockException e) {
            throw new CustomConflictException(USER_POINT_CHARGE_CONFLICT);
        }

        boolean payResult = paymentService.sendPayment(String.valueOf(userId), chargePoint);

        if (!payResult) {
            log.error("결제 시스템 결제 실패!");
            throw new CustomBadGateWayException(PAYMENT_BAD_GATEWAY);
        }

        return user;
    }
}
