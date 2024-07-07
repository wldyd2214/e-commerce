package com.hhplus.commerce.spring.api.controller.user;

import com.hhplus.commerce.spring.api.ApiResponse;
import com.hhplus.commerce.spring.api.controller.user.dto.UserDTOMapper;
import com.hhplus.commerce.spring.api.controller.user.request.BalanceChargeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @PostMapping("/{userId}/charge")
    public ApiResponse<Object> userBalanceCharge(@PathVariable Long userId,
                                                 @RequestBody BalanceChargeRequest reqDTO) {
        return ApiResponse.ok(UserDTOMapper.createDummyBalanceChargeResponse(userId, reqDTO.getChargeAmount()));
    }
}
