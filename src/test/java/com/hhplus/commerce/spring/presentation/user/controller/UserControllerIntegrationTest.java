package com.hhplus.commerce.spring.presentation.user.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.commerce.spring.domain.user.entity.User;
import com.hhplus.commerce.spring.infrastructure.user.database.UserJpaRepository;
import com.hhplus.commerce.spring.presentation.common.exception.ErrorCode;
import com.hhplus.commerce.spring.presentation.common.exception.code.BadRequestErrorCode;
import com.hhplus.commerce.spring.presentation.user.dto.request.PointChargeRequestDTO;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
@SpringBootTest
public class UserControllerIntegrationTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    UserJpaRepository jpaRepository;

    private long userId;
    private String userName;
    private BigDecimal chargePoint;

    @BeforeEach
    void setUp() {
        userName = "제리";
        BigDecimal defaultPoint = new BigDecimal("0");
        chargePoint = new BigDecimal("100000");

        // 테스트 사용자 정보 준비
        User saveUser = User.createUser(userName, defaultPoint);
        jpaRepository.save(saveUser);
        userId = saveUser.getId();
    }

    @DisplayName("사용자 포인트 충전에 성공한다.")
    @Test
    void chargePoints() throws Exception {

        // given
        PointChargeRequestDTO request = createPointChargeRequestDTO(chargePoint);

        // when, then
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
                jsonPath("$.data.point").value(chargePoint)
            );
    }

    @DisplayName("사용자 포인트 충전 - 음수 충전")
    @Test
    void chargePointsPositive() throws Exception {

        //given
        BigDecimal positivePoint = new BigDecimal("-1000000");
        PointChargeRequestDTO request = createPointChargeRequestDTO(positivePoint);

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
        PointChargeRequestDTO request = createPointChargeRequestDTO(chargePoint);

        // when // then
        mockMvc.perform(
                post("/users/{userId}/charge", userId)
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpectAll(
                assertErrorResponse(BadRequestErrorCode.USER_BAD_REQUEST)
            );
    }

    private PointChargeRequestDTO createPointChargeRequestDTO(BigDecimal point) {
        return new PointChargeRequestDTO(point);
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
