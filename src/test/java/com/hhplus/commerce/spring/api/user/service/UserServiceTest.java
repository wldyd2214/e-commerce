package com.hhplus.commerce.spring.api.user.service;

import static com.hhplus.commerce.spring.presentation.common.exception.code.BadGateWayErrorCode.PAYMENT_BAD_GATEWAY;
import static com.hhplus.commerce.spring.presentation.common.exception.code.BadRequestErrorCode.USER_BAD_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import com.hhplus.commerce.spring.presentation.common.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.presentation.common.exception.CustomBadGateWayException;
import com.hhplus.commerce.spring.domain.order.service.PaymentService;
import com.hhplus.commerce.spring.old.api.user.model.User;
import com.hhplus.commerce.spring.old.api.user.repository.UserRepository;
import com.hhplus.commerce.spring.domain.user.service.UserService;
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

        given(userRepository.findByIdWithLock(userId)).willThrow(new CustomBadRequestException(USER_BAD_REQUEST));

        // when // then
        assertThatThrownBy(() -> userService.userBalanceCharge(userId, chargePoint))
            .isInstanceOf(CustomBadRequestException.class)
            .hasMessage(USER_BAD_REQUEST.getMessage());
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

        given(userRepository.findByIdWithLock(userId)).willReturn(Optional.ofNullable(createUser(userId, userName, userPoint)));

        given(paymentService.sendPayment(String.valueOf(userId), chargePoint))
            .willReturn(false);

        // when // then
        assertThatThrownBy(() -> userService.userBalanceCharge(userId, chargePoint))
            .isInstanceOf(CustomBadGateWayException.class)
            .hasMessage(PAYMENT_BAD_GATEWAY.getMessage());
    }

    @DisplayName("잔액 충전에 성공한다.")
    @Test
    void userBalanceCharge() {
        // given
        Long userId = 1L;
        String userName = "박지용";
        int userPoint = 100000;
        int chargePoint = 50000;

        given(userRepository.findByIdWithLock(userId)).willReturn(Optional.ofNullable(createUser(userId, userName, userPoint)));

        given(paymentService.sendPayment(String.valueOf(userId), chargePoint)).willReturn(true);

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