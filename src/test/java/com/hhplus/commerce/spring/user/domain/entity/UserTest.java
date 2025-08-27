package com.hhplus.commerce.spring.user.domain.entity;

import static com.hhplus.commerce.spring.user.domain.UserFixture.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.Assertions.*;

import com.hhplus.commerce.spring.common.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.common.exception.code.BadRequestErrorCode;
import com.hhplus.commerce.spring.user.domain.command.UserCommand;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserTest {
    PasswordEncoder passwordEncoder;
    User user;

    @BeforeEach
    void setUp() {
        passwordEncoder = createPasswordEncoder();

        var command = UserCommand.Register.of("jypark@commerce.app", "박지용", "verysecret");
        user = User.create(command, passwordEncoder);
    }

    @Test
    @DisplayName("비밀번호 일치 여부 검증")
    void verifyPassword() {
        assertThat(user.verifyPassword("verysecret", passwordEncoder)).isTrue();
        assertThat(user.verifyPassword("test", passwordEncoder)).isFalse();
    }

    @Test
    @DisplayName("사용자 포인트 충전 - 실패")
    void chargePointFail() {
        // when, then
        assertThatThrownBy(() -> user.chargePoint(BigDecimal.ZERO))
            .isInstanceOf(CustomBadRequestException.class)
            .hasMessage(BadRequestErrorCode.AMOUNT_MUST_BE_POSITIVE.getMessage());
    }

    @Test
    @DisplayName("사용자 포인트 충전 - 성공")
    void chargePoint() {
        // given
        BigDecimal chargePoint = new BigDecimal(1000);

        // when
        user.chargePoint(chargePoint);

        // then
        assertThat(user.getPoint()).isEqualTo(chargePoint);
    }
}