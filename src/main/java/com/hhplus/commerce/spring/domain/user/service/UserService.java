package com.hhplus.commerce.spring.domain.user.service;

import com.hhplus.commerce.spring.domain.user.dto.UserCommand;
import com.hhplus.commerce.spring.old.api.user.model.User;

public interface UserService {

    User findUserById(Long userId);

    User chargeUserPoints(UserCommand.PointCharge toPointCharge);
}
