package com.hhplus.commerce.spring.domain.user.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.hhplus.commerce.spring.domain.payment.service.PaymentService;
import com.hhplus.commerce.spring.domain.user.dto.UserCommand;
import com.hhplus.commerce.spring.domain.user.entity.User;
import com.hhplus.commerce.spring.domain.user.repository.UserRepository;
import com.hhplus.commerce.spring.domain.user.service.UserService;
import com.hhplus.commerce.spring.infrastructure.user.database.UserJpaRepository;
import com.hhplus.commerce.spring.presentation.common.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.presentation.common.exception.code.BadRequestErrorCode;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
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
    UserRepository userRepository;
    @Autowired
    UserJpaRepository jpaRepository;
    @Autowired
    PaymentService paymentService;
    @Autowired
    UserService userService;

    @DisplayName("사용자 정보 조회에 성공한다.")
    @Test
    void findUserById() {
        // given
        String name = "제리";
        BigDecimal point = new BigDecimal("0");
        User saveUser = createUserEntity(name, point);

        jpaRepository.save(saveUser);

        // when
//        User user = userService.findUserById(saveUser.getId());

        // then
//        assertThat(user).isNotNull();
//        assertThat(user).extracting("id", "name", "point")
//                        .contains(saveUser.getId(), name, point);
    }

    @DisplayName("존재하지 않은 사용자 정보 조회시 실패한다.")
    @Test
    void findUserByIdIsEmpty() {
        // given
        long userId = -1;

        // when, then
        assertThatThrownBy(() -> userService.findUserById(userId))
            .isInstanceOf(CustomBadRequestException.class)
            .hasMessage(BadRequestErrorCode.USER_BAD_REQUEST.getMessage());
    }

    @DisplayName("사용자 포인트를 충전한다.")
    @Test
    void chargeUserPoints() {
        // given
        String name = "제리";
        BigDecimal defaultPoint = new BigDecimal("0");
        User saveUser = createUserEntity(name, defaultPoint);

        jpaRepository.save(saveUser);

        BigDecimal chargePoint = new BigDecimal("1000");
        UserCommand.PointCharge command = createPointChargeCommand(saveUser.getId(), chargePoint);

        // when
//        User user = userService.chargeUserPoints(command);

        // then
//        assertThat(user).isNotNull();
//        assertThat(user).extracting("id", "name", "point")
//                        .contains(saveUser.getId(), name, defaultPoint.add(chargePoint));
    }

    private User createUserEntity(String name, BigDecimal point) {
        return new User(name, point);
    }

    private UserCommand.PointCharge createPointChargeCommand(long userId, BigDecimal point) {
        return UserCommand.PointCharge.builder()
                                      .userId(userId)
                                      .chargePoint(point)
                                      .build();
    }
}
