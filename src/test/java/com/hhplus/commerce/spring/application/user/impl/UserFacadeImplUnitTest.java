package com.hhplus.commerce.spring.application.user.impl;

import com.hhplus.commerce.spring.application.user.dto.request.UserPointChargeRequest;
import com.hhplus.commerce.spring.domain.user.dto.UserCommand;
import com.hhplus.commerce.spring.domain.user.service.UserService;
import com.hhplus.commerce.spring.old.api.user.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class UserFacadeImplUnitTest {
    @Mock
    UserService userService;
    @InjectMocks
    UserFacadeImpl userFacade;

    @DisplayName("사용자 포인트 충전 서비스가 호출되는지 검증")
    @Test
    void processUserPointCharge() {
        // given
        long userId = 1;
        Integer point = 1000;
        UserPointChargeRequest request = createUserPointChargeRequest(userId, point);

        String name = "제리";
        given(userService.chargeUserPoints(any(UserCommand.PointCharge.class))).willReturn(createUser(userId, name, point));
        
        // when
        userFacade.processUserPointCharge(request);
        
        // then
        verify(userService, times(1)).chargeUserPoints(any(UserCommand.PointCharge.class));
    }

    private UserPointChargeRequest createUserPointChargeRequest(Long userId, Integer point) {
        return UserPointChargeRequest.builder()
                                     .userId(userId)
                                     .point(point)
                                     .build();
    }

    private User createUser(Long id, String name, Integer point) {
        return User.builder()
                   .id(id)
                   .name(name)
                   .point(point)
                   .build();
    }
}