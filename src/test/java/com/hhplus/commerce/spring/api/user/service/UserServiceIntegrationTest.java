package com.hhplus.commerce.spring.api.user.service;

import com.hhplus.commerce.spring.presentation.common.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.old.api.user.infrastructure.database.UserJpaRepository;
import com.hhplus.commerce.spring.old.api.user.model.User;
import com.hhplus.commerce.spring.old.api.user.repository.UserRepository;
import com.hhplus.commerce.spring.domain.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.CompletableFuture;

import static com.hhplus.commerce.spring.presentation.common.exception.code.BadRequestErrorCode.USER_BAD_REQUEST;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
public class UserServiceIntegrationTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private UserService userService;

    @DisplayName("사용자 포인트 충전 동시성 테스트")
    @Test
    void userPointChargeAsync() {
        // given
        User saveUser = userJpaRepository.save(createUser("잔액 충전 동시성 테스트", 0));
        long userId = saveUser.getId();

        int chargePoint = 10000;
        int successCount = 1;

        // when
        CompletableFuture.allOf(
            CompletableFuture.runAsync(() -> userService.userBalanceCharge(userId, chargePoint))
                             .handle((result, ex) -> {
                                 if (ex != null) log.error("1. 잔액 충전실패!");
                                 return "";
                             }),
            CompletableFuture.runAsync(() -> userService.userBalanceCharge(userId, chargePoint))
                             .handle((result, ex) -> {
                                 if (ex != null) log.error("2. 잔액 충전실패!");
                                 return "";
                             }),
            CompletableFuture.runAsync(() -> userService.userBalanceCharge(userId, chargePoint))
                             .handle((result, ex) -> {
                                 if (ex != null) log.error("3. 잔액 충전실패!");
                                 return "";
                             }),
            CompletableFuture.runAsync(() -> userService.userBalanceCharge(userId, chargePoint))
                             .handle((result, ex) -> {
                                 if (ex != null) log.error("4. 잔액 충전실패!");
                                 return "";
                             }),
            CompletableFuture.runAsync(() -> userService.userBalanceCharge(userId, chargePoint))
                             .handle((result, ex) -> {
                                 if (ex != null) log.error("5. 잔액 충전실패!");
                                 return "";
                             })
        ).join();

        User findUser = userRepository.findById(userId)
                                      .orElseThrow(() -> new CustomBadRequestException(USER_BAD_REQUEST));

        // then
        assertThat(findUser.getUserPoint()).isEqualTo(saveUser.getUserPoint() + (chargePoint * successCount));
    }

    private User createUser(String userName, int userPoint) {
        return User.builder()
                   .userName(userName)
                   .userPoint(userPoint)
                   .build();
    }
}
