package com.hhplus.commerce.spring.user.domain.service;

import com.hhplus.commerce.spring.user.domain.command.UserCommand;
import com.hhplus.commerce.spring.user.domain.dto.UserSummaryInfo;

public interface UserService {

    // 사용자 포인트 충전
    UserSummaryInfo chargeUserPoints(UserCommand.ChargePoint command);
}
