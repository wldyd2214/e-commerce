package com.hhplus.commerce.spring.old.api.user.controller;

import com.hhplus.commerce.spring.presentation.common.ApiResponse;
import com.hhplus.commerce.spring.old.api.user.controller.dto.UserDTOMapper;
import com.hhplus.commerce.spring.old.api.user.controller.request.BalanceChargeRequest;
import com.hhplus.commerce.spring.old.api.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @Operation(
        summary = "사용자 잔액 충전/조회 API",
        description = "사용자 잔액을 충전하고 정보를 반환 합니다."
    )
    @PostMapping("/{userId}/charge")
    public ApiResponse<Object> userBalanceCharge(@PathVariable Long userId,
                                                 @RequestBody @Valid BalanceChargeRequest reqDTO) {
        return ApiResponse.ok(UserDTOMapper.toBalanceChargeResponse(
            userService.userBalanceCharge(userId, reqDTO.getChargePoint())));
    }
}
