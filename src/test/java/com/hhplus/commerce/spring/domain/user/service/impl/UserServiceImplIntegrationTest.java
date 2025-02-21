package com.hhplus.commerce.spring.domain.user.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.hhplus.commerce.spring.domain.payment.service.PaymentService;
import com.hhplus.commerce.spring.domain.user.dto.UserCommand;
import com.hhplus.commerce.spring.domain.user.dto.UserInfo;
import com.hhplus.commerce.spring.domain.user.entity.User;
import com.hhplus.commerce.spring.domain.user.mapper.UserMapper;
import com.hhplus.commerce.spring.domain.user.repository.UserRepository;
import com.hhplus.commerce.spring.domain.user.service.UserService;
import com.hhplus.commerce.spring.infrastructure.user.database.UserJpaRepository;
import com.hhplus.commerce.spring.presentation.common.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.presentation.common.exception.code.BadRequestErrorCode;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class UserServiceImplIntegrationTest {

    @Autowired
    UserJpaRepository jpaRepository;

    @Autowired
    UserService userService;

    private String userName;
    private BigDecimal defaultPoint;
    private BigDecimal chargePoint;

    @BeforeEach
    void setUp() {
        userName = "제리";
        defaultPoint = new BigDecimal("0");
        chargePoint = new BigDecimal("100000");
    }

    @DisplayName("사용자 정보 조회 - 성공")
    @Test
    void findUserById() {
        // given
        User saveUser = createUserEntity(userName, defaultPoint);
        jpaRepository.save(saveUser);

        // when
        UserInfo result = userService.findUserInfoById(saveUser.getId());

        // then
        assertThat(result).isNotNull();
        assertThat(result).extracting("id", "name", "point")
                          .contains(saveUser.getId(), userName, defaultPoint);
    }

    @DisplayName("사용자 정보 조회 - 존재하지 않은 사용자 정보")
    @Test
    void findUserByIdIsEmpty() {
        // given
        long userId = -1;

        // when, then
        assertThatThrownBy(() -> userService.findUserInfoById(userId))
            .isInstanceOf(CustomBadRequestException.class)
            .hasMessage(BadRequestErrorCode.USER_BAD_REQUEST.getMessage());
    }

    @DisplayName("사용자 포인트 충전 - 성공")
    @Test
    void chargeUserPoints() {
        // given
        User saveUser = createUserEntity(userName, defaultPoint);
        jpaRepository.save(saveUser);

        UserCommand.PointCharge command = createPointChargeCommand(saveUser.getId(), chargePoint);

        // when
        UserInfo result = userService.chargeUserPoints(command);

        // then
        assertThat(result).isNotNull();
        assertThat(result).extracting("id", "name", "point")
                          .contains(saveUser.getId(), userName, defaultPoint.add(chargePoint));
    }

    private User createUserEntity(String name, BigDecimal point) {
        return new User(name, point);
    }

    private UserCommand.PointCharge createPointChargeCommand(long userId, BigDecimal point) {
        return new UserCommand.PointCharge(userId, point);
    }
}
