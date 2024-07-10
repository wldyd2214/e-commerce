package com.hhplus.commerce.spring.api.service.user;

import com.hhplus.commerce.spring.api.service.payment.PaymentService;
import com.hhplus.commerce.spring.model.entity.User;
import com.hhplus.commerce.spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final PaymentService paymentService;

    private final UserRepository userRepository;

    @Transactional
    public User userBalanceCharge(Long userId, int chargePoint) {

        if (chargePoint < 0) throw new IllegalArgumentException("충전 액수는 0보다 커야 합니다.");

        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 사용자"));

        boolean payResult = paymentService.sendPayment(String.valueOf(userId), chargePoint);

        if (!payResult) {
            throw new IllegalArgumentException("결제 시스템 결제 실패!");
        }

        user.pointCharge(chargePoint);

        return user;
    }
}
