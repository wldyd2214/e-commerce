package com.hhplus.commerce.spring.domain.user.service.impl;

import com.hhplus.commerce.spring.domain.user.dto.UserCommand;
import com.hhplus.commerce.spring.domain.user.repository.UserRepository;
import com.hhplus.commerce.spring.domain.user.service.UserService;
import com.hhplus.commerce.spring.domain.user.entity.UserEntity;
import com.hhplus.commerce.spring.old.api.user.model.User;
import com.hhplus.commerce.spring.presentation.common.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.presentation.common.exception.code.BadRequestErrorCode;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplUnitTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    @DisplayName("사용자 정보 조회에 성공한다.")
    @Test
    void findUserById() {
        // given
        long userId = 1;
        String name = "제리";
        BigDecimal point = new BigDecimal("0");
        UserEntity userEntity = createUserEntity(userId, name, point);

        given(userRepository.findById(userId)).willReturn(Optional.ofNullable(userEntity));

        // when
        User user = userService.findUserById(userId);

        // then
        assertThat(user).isNotNull();
        assertThat(user).extracting("id", "name", "point")
                        .contains(userId, name, point);
    }

    @DisplayName("존재하지 않은 사용자 정보 조회시 실패한다.")
    @Test
    void findUserByIdIsEmpty() {
        // given
        long userId = -1;

        given(userRepository.findById(userId)).willReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> userService.findUserById(userId))
            .isInstanceOf(CustomBadRequestException.class)
            .hasMessage(BadRequestErrorCode.USER_BAD_REQUEST.getMessage());
    }

    @DisplayName("사용자 포인트를 충전한다.")
    @Test
    void chargeUserPoints() {
        // given
        long userId = 1;
        String name = "제리";
        BigDecimal defaultPoint = new BigDecimal("0");
        UserEntity userEntity = createUserEntity(userId, name, defaultPoint);

        BigDecimal chargePoint = new BigDecimal("1000");
        UserCommand.PointCharge command = createPointChargeCommand(userId, chargePoint);

        given(userRepository.findById(userId)).willReturn(Optional.ofNullable(userEntity));

        // when
        User user = userService.chargeUserPoints(command);

        // then
        assertThat(user).isNotNull();
        assertThat(user).extracting("id", "name", "point")
                        .contains(userId, name, defaultPoint.add(chargePoint));
    }

    private UserEntity createUserEntity(Long id, String name, BigDecimal point) {
        return new UserEntity(id, name, point);
    }

    private UserCommand.PointCharge createPointChargeCommand(long userId, BigDecimal point) {
        return UserCommand.PointCharge.builder()
                                      .userId(userId)
                                      .chargePoint(point)
                                      .build();
    }
}
