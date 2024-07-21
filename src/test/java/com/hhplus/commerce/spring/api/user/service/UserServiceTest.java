package com.hhplus.commerce.spring.api.user.service;

import static com.hhplus.commerce.spring.api.common.presentation.exception.code.NotFoundErrorCode.USER_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import com.hhplus.commerce.spring.api.common.presentation.exception.CustomNotFoundException;
import com.hhplus.commerce.spring.api.order.service.PaymentService;
import com.hhplus.commerce.spring.api.user.model.User;
import com.hhplus.commerce.spring.api.user.repository.UserRepository;
import java.util.Optional;
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

    @Mock
    PaymentService paymentService;

    @InjectMocks
    UserService userService;

    @DisplayName("존재하지 않은 사용자 잔액 충전시 예외가 발생한다.")
    @Test
    void emptyUserPointCharge() {
        // given
        Long userId = 1L;
        int chargePoint = 100000;

        given(userRepository.findById(userId))
            .willThrow(new IllegalArgumentException("존재하지 않은 사용자"));

        // when // then
        assertThatThrownBy(() -> userService.userBalanceCharge(userId, chargePoint))
            .isInstanceOf(CustomNotFoundException.class)
            .hasMessage(USER_NOT_FOUND.getMessage());
    }

    @DisplayName("음수를 충전하는 경우 예외가 발생한다.")
    @Test
    void UserPointChargePositive() {
        // given
        Long userId = 1L;
        int chargePoint = -50000;

        // when // then
        assertThatThrownBy(() -> userService.userBalanceCharge(userId, chargePoint))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("충전 액수는 0보다 커야 합니다.");
    }

    @DisplayName("결제 시스템 결제 실패시 예외가 발생한다.")
    @Test
    void userBalanceChargePaySystemFail() {
        // given
        Long userId = 1L;
        String userName = "박지용";
        int userPoint = 100000;
        int chargePoint = 50000;

        given(userRepository.findById(userId)).willReturn(
            Optional.ofNullable(createUser(userId, userName, userPoint)));

        given(paymentService.sendPayment(String.valueOf(userId), chargePoint))
            .willReturn(false);

        // when // then
        assertThatThrownBy(() -> userService.userBalanceCharge(userId, chargePoint))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("결제 시스템 결제 실패!");
    }

    @DisplayName("잔액 충전에 성공한다.")
    @Test
    void userBalanceCharge() {
        // given
        Long userId = 1L;
        String userName = "박지용";
        int userPoint = 100000;
        int chargePoint = 50000;

        given(userRepository.findById(userId)).willReturn(
            Optional.ofNullable(createUser(userId, userName, userPoint)));

        given(paymentService.sendPayment(String.valueOf(userId), chargePoint))
            .willReturn(true);

        // when // then
        User user = userService.userBalanceCharge(userId, chargePoint);

        assertThat(user).isNotNull();
        assertThat(user)
            .extracting("id", "userName", "userPoint")
            .contains(userId, userName, userPoint + chargePoint);
    }

    private User createUser(Long userId, String userName, int userPoint) {
        return User.builder()
                   .id(userId)
                   .userName(userName)
                   .userPoint(userPoint)
                   .build();
    }
}