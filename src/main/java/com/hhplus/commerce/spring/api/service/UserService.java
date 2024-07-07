package com.hhplus.commerce.spring.api.service;

import com.hhplus.commerce.spring.domain.user.User;
import com.hhplus.commerce.spring.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User userBalanceCharge(Long userId, Long chargeAmount) {

        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 사용자"));

        return null;
    }
}
