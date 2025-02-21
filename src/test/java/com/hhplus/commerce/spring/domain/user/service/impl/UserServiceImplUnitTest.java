package com.hhplus.commerce.spring.domain.user.service.impl;

import com.hhplus.commerce.spring.domain.user.dto.UserCommand;
import com.hhplus.commerce.spring.domain.user.dto.UserInfo;
import com.hhplus.commerce.spring.domain.user.entity.User;
import com.hhplus.commerce.spring.domain.user.mapper.UserMapper;
import com.hhplus.commerce.spring.domain.user.repository.UserRepository;
import com.hhplus.commerce.spring.domain.user.service.UserService;
import com.hhplus.commerce.spring.presentation.common.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.presentation.common.exception.code.BadRequestErrorCode;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
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
    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserService userService;

    private long userId;
    private String userName;
    private BigDecimal chargePoint;

    @BeforeEach
    void setUp() {
        userId = 1;
        userName = "제리";
        chargePoint = new BigDecimal("100000");
    }

    @DisplayName("사용자 정보 조회 - userId")
    @Test
    void findUserById() {
        // given
        User user = createUser(userId, userName, chargePoint);
        UserInfo userInfo = createUserInfo(userId, userName, chargePoint);

        given(userRepository.findById(userId)).willReturn(Optional.of(user));
        given(userMapper.toUserInfo(user)).willReturn(userInfo);

        // when
        UserInfo result = userService.findUserInfoById(userId);

        // then
        assertThat(result).isNotNull();
        assertThat(result).extracting("id", "name", "point")
                          .contains(userId, userName, chargePoint);
    }

    @DisplayName("사용자 정보 조회 - 존재하지 않은 사용자 정보")
    @Test
    void findUserByIdIsEmpty() {
        // given
        userId = -1;

        given(userRepository.findById(userId)).willReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> userService.findUserInfoById(userId))
            .isInstanceOf(CustomBadRequestException.class)
            .hasMessage(BadRequestErrorCode.USER_BAD_REQUEST.getMessage());
    }

    @DisplayName("사용자 포인트 충전 - 성공")
    @Test
    void chargeUserPoints() {
        // given
        BigDecimal defaultPoint = new BigDecimal("0");
        User user = createUser(userId, userName, defaultPoint);
        UserInfo userInfo = createUserInfo(userId, userName, chargePoint);

        UserCommand.PointCharge command = createPointChargeCommand(userId, chargePoint);

        given(userRepository.findById(userId)).willReturn(Optional.of(user));
        given(userMapper.toUserInfo(user)).willReturn(userInfo);

        // when
        UserInfo result = userService.chargeUserPoints(command);

        // then
        assertThat(result).isNotNull();
        assertThat(result).extracting("id", "name", "point")
                          .contains(userId, userName, defaultPoint.add(chargePoint));
    }

    private User createUser(Long id, String name, BigDecimal point) {
        return new User(id, name, point);
    }

    private UserInfo createUserInfo(Long id, String name, BigDecimal point) {
        return new UserInfo(id, name, point);
    }

    private UserCommand.PointCharge createPointChargeCommand(long userId, BigDecimal point) {
        return new UserCommand.PointCharge(userId, point);
    }
}
