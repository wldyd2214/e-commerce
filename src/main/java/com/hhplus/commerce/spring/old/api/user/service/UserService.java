package com.hhplus.commerce.spring.old.api.user.service;

import com.hhplus.commerce.spring.presentation.common.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.presentation.common.exception.CustomConflictException;
import com.hhplus.commerce.spring.presentation.common.exception.CustomBadGateWayException;
import com.hhplus.commerce.spring.domain.order.service.PaymentService;
import com.hhplus.commerce.spring.old.api.user.model.User;
import com.hhplus.commerce.spring.old.api.user.repository.UserRepository;
import com.hhplus.commerce.spring.presentation.common.exception.code.BadGateWayErrorCode;
import com.hhplus.commerce.spring.presentation.common.exception.code.BadRequestErrorCode;
import com.hhplus.commerce.spring.presentation.common.exception.code.ConflictErrorCode;
import jakarta.persistence.OptimisticLockException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                                  .orElseThrow(() -> new CustomBadRequestException(
                                      BadRequestErrorCode.USER_BAD_REQUEST));

        try {
            user.pointCharge(chargePoint);
        } catch (OptimisticLockException e) {
            throw new CustomConflictException(ConflictErrorCode.USER_POINT_CHARGE_CONFLICT);
        }

        boolean payResult = paymentService.sendPayment(String.valueOf(userId), chargePoint);

        if (!payResult) {
            log.error("결제 시스템 결제 실패!");
            throw new CustomBadGateWayException(BadGateWayErrorCode.PAYMENT_BAD_GATEWAY);
        }

        return user;
    }
}
