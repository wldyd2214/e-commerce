package com.hhplus.commerce.spring.application.user.impl;

import com.hhplus.commerce.spring.application.user.dto.UserInfo;
import com.hhplus.commerce.spring.application.user.dto.request.UserPointChargeRequest;
import com.hhplus.commerce.spring.infrastructure.user.database.UserJpaRepository;
import com.hhplus.commerce.spring.infrastructure.user.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
class UserFacadeImplTest {
    @Autowired
    private UserFacadeImpl userFacade;
    @Autowired
    private UserJpaRepository userRepository;

    @Test
    void processUserPointCharge() {
        // given
        // 초기 상태 설정
        UserEntity userEntity = new UserEntity("제리", 0);
        userRepository.save(userEntity);

        long userId = userEntity.getId();
        int point = 1000;
        UserPointChargeRequest request = new UserPointChargeRequest(userId, point);

        // when
        UserInfo userInfo = userFacade.processUserPointCharge(request);

        // then
        assertThat(point).isEqualTo(userInfo.getPoint());
    }
}