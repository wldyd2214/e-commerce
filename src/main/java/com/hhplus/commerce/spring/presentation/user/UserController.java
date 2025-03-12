package com.hhplus.commerce.spring.presentation.user;

import com.hhplus.commerce.spring.application.user.UserFacade;
import com.hhplus.commerce.spring.application.user.dto.UserFacadeRequest;
import com.hhplus.commerce.spring.application.user.dto.UserFacadeResponse;
import com.hhplus.commerce.spring.presentation.common.ApiResponse;
import com.hhplus.commerce.spring.presentation.user.dto.request.PointChargeRequest;
import com.hhplus.commerce.spring.presentation.user.dto.request.UserRequest;
import com.hhplus.commerce.spring.presentation.user.dto.response.UserResponse;
import com.hhplus.commerce.spring.presentation.user.mapper.UserRequestMapper;
import com.hhplus.commerce.spring.presentation.user.mapper.UserResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "유저", description = "사용자 관련 인터페이스를 제공합니다.")
@RequestMapping(value = "/users")
public class UserController {

    private final UserFacade userFacade;

    private final UserRequestMapper requestMapper;
    private final UserResponseMapper responseMapper;

    /**
     * path 1안: /users/{userId}/charge
     * path 2안: /users/charge/{userId}
     * 1안 RESTful API에서 자원의 계층 구조를 표현할 때 일반적으로 "리소스/행동" 형태로 작성하는 것을 권장
     * chargePoints 메소드명: 포인트 관리 관련된 메소드가 단일 포인트가 아니라 포인트 총량에 변화를 주는 것이라면, chargePoint보다 복수형으로 표현
     */
    @Operation(summary = "사용자 잔액 충전/조회 API", description = "사용자 잔액을 충전합니다.")
    @PostMapping("/{userId}/charge")
    public ApiResponse<UserResponse> chargePoints(@PathVariable Long userId,
        @RequestBody @Valid UserRequest.PointCharge request) {

        // 1. Facade 요청 DTO 변환
        UserFacadeRequest.PointCharge chargeRequest = requestMapper.toPointCharge(userId, request.getChargePoint());

        // 2. Facade 포인트 충전 프로세스 호출
        UserFacadeResponse.PointCharge userInfo = userFacade.chargeUserPoints(chargeRequest);

        // 3. Facade 응답 컨트롤러 응답으로 변환
        UserResponse response = responseMapper.toUserResponse(userInfo);

        return ApiResponse.ok(response);
    }
}
