package com.hhplus.commerce.spring.api.user.service;

import com.hhplus.commerce.spring.api.order.service.PaymentService;
import com.hhplus.commerce.spring.api.user.model.User;
import com.hhplus.commerce.spring.api.user.repository.UserRepository;
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

        User user = userRepository.findByIdWithPessimisticLock(userId)
                                  .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 사용자"));

        user.pointCharge(chargePoint);
        //        for(int i = 0; i < 3; i++) {
//            try {
//                user.pointCharge(chargePoint);
//            } catch (OptimisticLockException e) {
//                if (i == 2) {
//                    log.error("실패!");
//                    throw e;
//                }
//            }
//        }

        boolean payResult = paymentService.sendPayment(String.valueOf(userId), chargePoint);

        if (!payResult) {
            log.error("결제 시스템 결제 실패!");
            throw new IllegalArgumentException("결제 시스템 결제 실패!");
        }

        return user;
    }
}
