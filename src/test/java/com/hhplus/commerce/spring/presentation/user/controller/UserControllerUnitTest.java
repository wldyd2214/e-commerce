package com.hhplus.commerce.spring.presentation.user.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.commerce.spring.application.user.UserFacade;
import com.hhplus.commerce.spring.application.user.dto.UserInfo;
import com.hhplus.commerce.spring.presentation.user.dto.request.PointChargeRequestDTO;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserController.class)
public class UserControllerUnitTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @MockBean
    protected UserFacade userFacade;

    @DisplayName("사용자 포인트 충전에 성공한다.")
    @Test
    void chargePoints() throws Exception {
        // given
        long userId = 1;
        String userName = "제리";
        BigDecimal point = new BigDecimal("1000");
        PointChargeRequestDTO request = createPointChargeRequestDTO(point);

        UserInfo userInfo = createUserInfo(userId, userName, point);
        given(userFacade.processUserPointCharge(any())).willReturn(userInfo);

        // when // then
        mockMvc.perform(
                   post(String.format("/users/%d/charge", userId))
                       .content(objectMapper.writeValueAsString(request))
                       .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpectAll(
                   status().isOk(),
                   jsonPath("$.code").value(HttpStatus.OK.value()),
                   jsonPath("$.status").value(HttpStatus.OK.name()),
                   jsonPath("$.message").value(HttpStatus.OK.name()),
                   jsonPath("$.data.id").value(userId),
                   jsonPath("$.data.name").value(userName),
                   jsonPath("$.data.point").value(point)
               );
    }

    @DisplayName("음수를 충전하는 경우 예외가 발생한다.")
    @Test
    void chargePointsPositive() throws Exception {
        // given
        long userId = 1;
        String userName = "제리";
        BigDecimal point = new BigDecimal("-1000");
        PointChargeRequestDTO request = createPointChargeRequestDTO(point);

        UserInfo userInfo = createUserInfo(userId, userName, point);
        given(userFacade.processUserPointCharge(any())).willReturn(userInfo);

        // when // then
        mockMvc.perform(
                   post(String.format("/users/%d/charge", userId))
                       .content(objectMapper.writeValueAsString(request))
                       .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpectAll(
                   status().isBadRequest(),
                   jsonPath("$.code").value(HttpStatus.BAD_REQUEST.value()),
                   jsonPath("$.status").value(HttpStatus.BAD_REQUEST.name()),
                   jsonPath("$.message").value("충전 액수는 0보다 커야 합니다."),
                   jsonPath("$.data").isEmpty()
               );
    }

    private PointChargeRequestDTO createPointChargeRequestDTO(BigDecimal point) {
        return PointChargeRequestDTO.builder()
                                    .chargePoint(point)
                                    .build();
    }

    private UserInfo createUserInfo(Long userId, String userName, BigDecimal point) {
        return UserInfo.builder()
                       .id(userId)
                       .name(userName)
                       .point(point)
                       .build();
    }

}
