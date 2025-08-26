package com.hhplus.commerce.spring.user.domain.service;

import static org.assertj.core.api.Assertions.assertThat;

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
        var command = UserCommand.Register.of("jypark@commerce.app", "박지용", "verysecret");

        // when
        UserSummaryInfo userInfo = userService.register(command);

        // that
        assertThat(userInfo.getId()).isNotNull();
        assertThat(userInfo.getEmail()).isEqualTo(command.getEmail());
    }
    
    @Test
    void chargeUserPoints() {
        // given
        var registerCommand = UserCommand.Register.of("jypark@commerce.app", "박지용", "verysecret");
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