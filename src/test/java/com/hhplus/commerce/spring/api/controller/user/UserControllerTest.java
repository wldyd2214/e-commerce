package com.hhplus.commerce.spring.api.controller.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.commerce.spring.api.controller.user.request.BalanceChargeRequest;
import com.hhplus.commerce.spring.api.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected UserService userService;

    @DisplayName("사용자 포인트 충전에 성공한다.")
    @Test
    void userBalanceCharge() throws Exception {
        // given
        Long userId = 1000L;
        Long chargeAmount = 100000L;

        BalanceChargeRequest request = BalanceChargeRequest.builder()
                                                           .chargeAmount(chargeAmount)
                                                           .build();

        // when // then
        mockMvc.perform(
                   post(String.format("/users/%d/charge", userId))
                       .content(objectMapper.writeValueAsString(request))
                       .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpectAll(
                   status().isOk(),
                   jsonPath("$.code").value("200"),
                   jsonPath("$.status").value("OK"),
                   jsonPath("$.message").value("OK"),
                   jsonPath("$.data.userId").value(userId),
                   jsonPath("$.data.balanceAmount").value(chargeAmount)
               );
    }

    @DisplayName("사용자 포인트 충전시 충전 금액에 음수를 요청하는 경우 예외가 발생한다.")
    @Test
    void userBalanceChargePositive() throws Exception {
        // given
        Long userId = 1000L;
        Long chargeAmount = -10L;

        BalanceChargeRequest request = BalanceChargeRequest.builder()
                                                           .chargeAmount(chargeAmount)
                                                           .build();

        // when // then
        mockMvc.perform(
                   post(String.format("/users/%d/charge", userId))
                       .content(objectMapper.writeValueAsString(request))
                       .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpectAll(
                   status().isBadRequest(),
                   jsonPath("$.code").value("400"),
                   jsonPath("$.status").value("BAD_REQUEST"),
                   jsonPath("$.message").value("충전 액수는 0보다 커야 합니다."),
                   jsonPath("$.data").isEmpty()
               );
    }
}