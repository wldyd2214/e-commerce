package com.hhplus.commerce.spring.api.user.service;

import com.hhplus.commerce.spring.api.order.service.PaymentService;
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
    private PaymentService paymentService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @DisplayName("사용자 포인트 충전 동시성 테스트")
    @Test
    void userPointChargeAsync() {
        // given
        long userId = 1;
        int chargePoint = 10000;

        // when
        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> userService.userBalanceCharge(userId, chargePoint))
                                 .handle((result, ex) -> {
                                     if (ex != null) System.out.println("1번 async 충전");
                                     return "";}),
                CompletableFuture.runAsync(() -> userService.userBalanceCharge(userId, chargePoint))
                                 .handle((result, ex) -> {
                                     if (ex != null) System.out.println("2번 async 충전");
                                     return "";}),
                CompletableFuture.runAsync(() -> userService.userBalanceCharge(userId, chargePoint))
                                 .handle((result, ex) -> {
                                     if (ex != null) System.out.println("3번 async 충전");
                                     return "";}),
                CompletableFuture.runAsync(() -> userService.userBalanceCharge(userId, chargePoint))
                                 .handle((result, ex) -> {
                                     if (ex != null) System.out.println("4번 async 충전");
                                     return "";}),
                CompletableFuture.runAsync(() -> userService.userBalanceCharge(userId, chargePoint))
                                 .handle((result, ex) -> {
                                     if (ex != null) System.out.println("5번 async 충전");
                                     return "";})
        ).join();

        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new IllegalArgumentException("미존재 사용자"));

        // then
        assertThat(user).extracting("id", "userPoint")
                        .contains(userId, chargePoint);
    }

}
