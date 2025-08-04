package com.hhplus.commerce.spring.member.presentation;

import com.hhplus.commerce.spring.member.application.UserFacade;
import com.hhplus.commerce.spring.member.presentation.dto.UserPointChargeRequest;
import com.hhplus.commerce.spring.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "User", description = "사용자 관련 인터페이스를 제공합니다.")
@RequestMapping(value = "/users")
public class UserController {
    private final UserFacade userFacade;

    @Operation(summary = "사용자 잔액 충전/조회 API", description = "사용자 잔액을 충전합니다.")
    @PostMapping("/{userId}/points")
    public ApiResponse<Void> chargePoints(@PathVariable Long userId, @RequestBody @Valid UserPointChargeRequest request) {
        // 포인트 충전
//        UserPointChargeResponse response = userFacade.chargeUserPoints();
        return ApiResponse.ok(null);
    }
}
