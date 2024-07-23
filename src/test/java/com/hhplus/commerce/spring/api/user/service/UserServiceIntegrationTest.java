package com.hhplus.commerce.spring.api.user.service;

import com.hhplus.commerce.spring.api.user.infrastructure.database.UserJpaRepository;
import com.hhplus.commerce.spring.api.user.model.User;
import com.hhplus.commerce.spring.api.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

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
        User saveUser = userJpaRepository.save(createUser());
        long userId = saveUser.getId();

        int chargePoint = 10000;
        int runAsyncNum = 1;

        // when
        CompletableFuture.allOf(
            CompletableFuture.runAsync(() -> userService.userBalanceCharge(userId, chargePoint))
                             .handle((result, ex) -> {
                                 if (ex != null) System.out.println("1 잔액 충전 실패!");
                                 return "";
                             }),
            CompletableFuture.runAsync(() -> userService.userBalanceCharge(userId, chargePoint))
                             .handle((result, ex) -> {
                                 if (ex != null) System.out.println("2 잔액 충전 실패!");
                                 return "";
                             }),
            CompletableFuture.runAsync(() -> userService.userBalanceCharge(userId, chargePoint))
                             .handle((result, ex) -> {
                                 if (ex != null) System.out.println("3 잔액 충전 실패!");
                                 return "";
                             }),
            CompletableFuture.runAsync(() -> userService.userBalanceCharge(userId, chargePoint))
                             .handle((result, ex) -> {
                                 if (ex != null) System.out.println("4 잔액 충전 실패!");
                                 return "";
                             }),
            CompletableFuture.runAsync(() -> userService.userBalanceCharge(userId, chargePoint))
                             .handle((result, ex) -> {
                                 if (ex != null) System.out.println("5 잔액 충전 실패!");
                                 return "";
                             })
        ).join();

        User findUser = userRepository.findById(userId)
                                  .orElseThrow(() -> new IllegalArgumentException("미존재 사용자"));

        // then
        assertThat(findUser.getUserPoint()).isEqualTo(saveUser.getUserPoint() + (chargePoint * runAsyncNum));

        userJpaRepository.deleteById(saveUser.getId());
    }

    private User createUser() {
        return User.builder()
                   .userName("동시성 테스트 사용자")
                   .userPoint(0)
                   .build();
    }
}
