package com.hhplus.commerce.spring.user.domain.service;

import static com.hhplus.commerce.spring.user.domain.UserFixture.createUserRegisterCommand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.hhplus.commerce.spring.common.exception.CustomConflictException;
import com.hhplus.commerce.spring.user.domain.command.UserCommand;
import com.hhplus.commerce.spring.user.domain.dto.UserSummaryInfo;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;


@ActiveProfiles("test")
@SpringBootTest
@Transactional
public record UserServiceImplTest(UserService userService) {

    @Test
    void register() {
        // given
        var command = createUserRegisterCommand();

        // when
        UserSummaryInfo userInfo = userService.register(command);

        // that
        assertThat(userInfo.getId()).isNotNull();
        assertThat(userInfo.getEmail()).isEqualTo(command.getEmail());
    }

    @Test
    void registerDuplicateEmail() {
        // given - 회원 정보 등록
        var givenCommand = createUserRegisterCommand();
        userService.register(givenCommand);

        // given - 중복된 회원 정보 데이터
        var command = createUserRegisterCommand();

        // when, that
        assertThatThrownBy(() -> userService.register(command))
            .isInstanceOf(CustomConflictException.class);
    }
    
    @Test
    void chargeUserPoints() {
        // given
        var registerCommand = createUserRegisterCommand();
        UserSummaryInfo userRegisterInfo = userService.register(registerCommand);

        BigDecimal chargePoint = BigDecimal.valueOf(100);
        var command = UserCommand.ChargePoint.of(userRegisterInfo.getId(), chargePoint);

        // when
        UserSummaryInfo userInfo = userService.chargeUserPoints(command);

        // that
        assertThat(userInfo.getId()).isNotNull();
        assertThat(userInfo.getPoint()).isEqualTo(command.getChargePoint());
    }
}