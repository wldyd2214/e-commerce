package com.hhplus.commerce.spring.presentation.user.controller;

import com.hhplus.commerce.spring.application.user.UserFacade;
import com.hhplus.commerce.spring.application.user.dto.request.UserPointChargeRequest;
import com.hhplus.commerce.spring.presentation.common.ApiResponse;
import com.hhplus.commerce.spring.presentation.user.dto.request.PointChargeRequestDTO;
import com.hhplus.commerce.spring.presentation.user.dto.response.UserResponseDTO;
import com.hhplus.commerce.spring.presentation.user.mapper.UserDTORequestMapper;
import com.hhplus.commerce.spring.presentation.user.mapper.UserDTOResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserController {
    private final UserFacade userFacade;

    /**
     * path 1안: /users/{userId}/charge
     * path 2안: /users/charge/{userId}
     * 1안 RESTful API에서 자원의 계층 구조를 표현할 때 일반적으로 "리소스/행동" 형태로 작성하는 것을 권장
     *
     * chargePoints 메소드명: 포인트 관리 관련된 메소드가 단일 포인트가 아니라 포인트 총량에 변화를 주는 것이라면, chargePoint보다 복수형으로 표현
     */
    @Operation(
            summary = "사용자 잔액 충전/조회 API",
            description = "사용자 잔액을 충전하고 정보를 반환 합니다."
    )
    @PostMapping("/{userId}/charge")
    public ApiResponse<UserResponseDTO> chargePoints(@PathVariable Long userId,
                                                     @RequestBody @Valid PointChargeRequestDTO reqDTO) {

        UserPointChargeRequest request =
                UserDTORequestMapper.toUserPointChargeRequest(userId, reqDTO.getChargePoint());

        UserResponseDTO responseDTO =
                UserDTOResponseMapper.toUserResponseDTO(userFacade.processUserPointCharge(request));

        return ApiResponse.ok(responseDTO);
    }
}
