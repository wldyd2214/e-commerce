package com.hhplus.commerce.spring.user.presentation;

import com.hhplus.commerce.spring.user.domain.dto.UserSummaryInfo;
import com.hhplus.commerce.spring.user.domain.service.UserService;
import com.hhplus.commerce.spring.user.presentation.docs.SignUpApiErrorResponseDocs;
import com.hhplus.commerce.spring.user.presentation.request.UserPointChargeRequest;
import com.hhplus.commerce.spring.common.ApiResponse;
import com.hhplus.commerce.spring.user.presentation.request.UserSignUpRequest;
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
    private final UserService userService;

    @SignUpApiErrorResponseDocs
    @Operation(summary = "회원가입 API", description = "서비스를 이용하기 위한 회원 정보를 생성합니다.")
    @PostMapping("")
    public ApiResponse<UserSummaryInfo> signUp(@RequestBody @Valid UserSignUpRequest request) {
        return ApiResponse.ok(userService.register(request.toCommand()));
    }

    @Operation(summary = "사용자 잔액 충전/조회 API", description = "사용자 잔액을 충전합니다.")
    @PostMapping("/{userId}/points")
    public ApiResponse<UserSummaryInfo> chargePoints(@PathVariable("userId") Long userId, @RequestBody @Valid UserPointChargeRequest request) {
        return ApiResponse.ok(userService.chargeUserPoints(request.toCommand(userId)));
    }
}
