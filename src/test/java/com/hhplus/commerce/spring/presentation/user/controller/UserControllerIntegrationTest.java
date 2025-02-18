package com.hhplus.commerce.spring.presentation.user.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.hhplus.commerce.spring.domain.user.entity.User;
import com.hhplus.commerce.spring.infrastructure.user.database.UserJpaRepository;
import com.hhplus.commerce.spring.presentation.common.ApiResponse;
import com.hhplus.commerce.spring.presentation.user.UserController;
import com.hhplus.commerce.spring.presentation.user.dto.request.PointChargeRequestDTO;
import com.hhplus.commerce.spring.presentation.user.dto.response.UserResponseDTO;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class UserControllerIntegrationTest {
    @Autowired
    UserJpaRepository jpaRepository;
    @Autowired
    UserController userController;

    @DisplayName("사용자 포인트 충전에 성공한다.")
    @Test
    void chargePoints() {
        // given
        String name = "제리";
        BigDecimal point = new BigDecimal("0");
        User saveUser = createUserEntity(name, point);
        jpaRepository.save(saveUser);

        PointChargeRequestDTO requestDTO = createPointChargeRequestDTO(point);

        // when
        ApiResponse<UserResponseDTO> response = userController.chargePoints(saveUser.getId(), requestDTO);

        // then
        assertThat(response).isNotNull();
        assertThat(response).extracting("id", "name", "point")
                            .contains(saveUser.getId(), name, point);
    }

    private User createUserEntity(String name, BigDecimal point) {
        return new User(name, point);
    }

    private PointChargeRequestDTO createPointChargeRequestDTO(BigDecimal point) {
        return PointChargeRequestDTO.builder()
                                    .chargePoint(point)
                                    .build();
    }

}
