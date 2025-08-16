package com.hhplus.commerce.spring.user.domain.entity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.Assertions.*;

import com.hhplus.commerce.spring.common.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.common.exception.code.BadRequestErrorCode;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    User user;

    @BeforeEach
    void setUp() {
        user = User.create("박지용");
    }

    @DisplayName("사용자 포인트 충전 - 실패")
    @Test
    void chargePointFail() {
        // when, then
        assertThatThrownBy(() -> user.chargePoint(BigDecimal.ZERO))
            .isInstanceOf(CustomBadRequestException.class)
            .hasMessage(BadRequestErrorCode.AMOUNT_MUST_BE_POSITIVE.getMessage());
    }

    @DisplayName("사용자 포인트 충전 - 성공")
    @Test
    void chargePoint() {
        // given
        BigDecimal chargePoint = new BigDecimal(1000);

        // when
        user.chargePoint(chargePoint);

        // then
        assertThat(user.getPoint()).isEqualTo(chargePoint);
    }
}