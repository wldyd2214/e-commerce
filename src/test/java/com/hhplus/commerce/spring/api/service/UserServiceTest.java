package com.hhplus.commerce.spring.api.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import com.hhplus.commerce.spring.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @DisplayName("존재하지 않은 사용자 잔액 충전시 예외가 발생한다.")
    @Test
    void emptyUserBalanceCharge() {
        // given
        Long userId = 1L;
        Long amount = 100000L;

        given(userRepository.findById(userId))
            .willThrow(new IllegalArgumentException("존재하지 않은 사용자"));

        // when // then
        assertThatThrownBy(() -> userService.userBalanceCharge(userId, amount))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("존재하지 않은 사용자");
    }
}