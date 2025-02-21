package com.hhplus.commerce.spring.presentation.user.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.commerce.spring.application.user.UserFacade;
import com.hhplus.commerce.spring.application.user.dto.UserFacadeRequest;
import com.hhplus.commerce.spring.application.user.dto.UserFacadeResponse;
import com.hhplus.commerce.spring.presentation.common.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.presentation.common.exception.ErrorCode;
import com.hhplus.commerce.spring.presentation.common.exception.code.BadRequestErrorCode;
import com.hhplus.commerce.spring.presentation.user.UserController;
import com.hhplus.commerce.spring.presentation.user.dto.request.PointChargeRequestDTO;
import com.hhplus.commerce.spring.presentation.user.dto.response.UserResponse;
import com.hhplus.commerce.spring.presentation.user.mapper.UserRequestMapper;
import com.hhplus.commerce.spring.presentation.user.mapper.UserResponseMapper;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

@WebMvcTest(UserController.class)
public class UserControllerUnitTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @MockBean
    protected UserFacade userFacade;
    @MockBean
    protected UserRequestMapper requestMapper;
    @MockBean
    protected UserResponseMapper responseMapper;


    private static long userId;
    private static String userName;
    private static BigDecimal point;

    @BeforeAll
    static void beforeAll() {
        userId = 1;
        userName = "제리";
        point = new BigDecimal("100000");
    }

    @DisplayName("사용자 포인트 충전 - 성공")
    @Test
    void chargePoints() throws Exception {

        // given
        PointChargeRequestDTO request = createPointChargeRequestDTO(point);

        // 1. 사용자 포인트 충전 요청 메퍼 준비
        UserFacadeRequest.PointCharge facadePointCharge = new UserFacadeRequest.PointCharge(userId, point);
        given(requestMapper.toPointCharge(userId, request.getChargePoint())).willReturn(facadePointCharge);

        // 2. 사용자 포인트 충전 준비
        UserFacadeResponse.PointCharge pointCharge = createPointCharge(userId, userName, point);
        given(userFacade.chargeUserPoints(facadePointCharge)).willReturn(pointCharge);

        // 3. 사용자 포인트 충전 응답 매퍼 준비
        UserResponse responseDTO = new UserResponse(userId, userName, point);
        given(responseMapper.toUserResponse(pointCharge)).willReturn(responseDTO);

        // when // then
        mockMvc.perform(
                   post("/users/{userId}/charge", userId)
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

    @DisplayName("사용자 포인트 충전 - 음수 충전")
    @Test
    void chargePointsPositive() throws Exception {

        // given
        PointChargeRequestDTO request = createPointChargeRequestDTO(new BigDecimal("-100000"));

        // 1. 사용자 포인트 충전 요청 메퍼 준비
        UserFacadeRequest.PointCharge facadePointCharge = new UserFacadeRequest.PointCharge(userId, point);
        given(requestMapper.toPointCharge(userId, request.getChargePoint())).willReturn(facadePointCharge);

        // 2. 사용자 포인트 충전 준비
        given(userFacade.chargeUserPoints(facadePointCharge)).willThrow(new CustomBadRequestException(
            BadRequestErrorCode.POSITIVE_POINT_BAD_REQUEST));

        // when // then
        mockMvc.perform(
                    post("/users/{userId}/charge", userId)
                       .content(objectMapper.writeValueAsString(request))
                       .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isBadRequest())
               .andExpectAll(
                   assertErrorResponse(BadRequestErrorCode.POSITIVE_POINT_BAD_REQUEST)
               );
    }

    @DisplayName("사용자 포인트 충전 - 존재하지 않은 사용자")
    @Test
    void chargePointsUserNotExist() throws Exception {

        // given
        userId = -1;
        PointChargeRequestDTO request = createPointChargeRequestDTO(point);

        // 1. 사용자 포인트 충전 요청 메퍼 준비
        UserFacadeRequest.PointCharge facadePointCharge = new UserFacadeRequest.PointCharge(userId, point);
        given(requestMapper.toPointCharge(userId, request.getChargePoint())).willReturn(facadePointCharge);

        // 2. 사용자 포인트 충전 준비
        given(userFacade.chargeUserPoints(facadePointCharge)).willThrow(new CustomBadRequestException(
            BadRequestErrorCode.POSITIVE_POINT_BAD_REQUEST));

        // when // then
        mockMvc.perform(
                post("/users/{userId}/charge", userId)
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpectAll(
                assertErrorResponse(BadRequestErrorCode.POSITIVE_POINT_BAD_REQUEST)
            );
    }

    private PointChargeRequestDTO createPointChargeRequestDTO(BigDecimal point) {
        return new PointChargeRequestDTO(point);
    }

    private UserFacadeResponse.PointCharge createPointCharge(Long userId, String userName, BigDecimal point) {
        return new UserFacadeResponse.PointCharge(userId, userName, point);
    }

    // 공통 예외 응답 검증 메서드
    private ResultMatcher[] assertErrorResponse(ErrorCode errorCode) {
        return new ResultMatcher[]{
            jsonPath("$.code").value(errorCode.getHttpStatus().value()),
            jsonPath("$.status").value(errorCode.getHttpStatus().name()),
            jsonPath("$.message").value(errorCode.getMessage()),
            jsonPath("$.data").isEmpty()
        };
    }
}
