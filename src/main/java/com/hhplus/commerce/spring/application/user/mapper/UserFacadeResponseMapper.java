package com.hhplus.commerce.spring.application.user.mapper;

import com.hhplus.commerce.spring.application.user.dto.UserInfo;
import com.hhplus.commerce.spring.domain.user.dto.User;

public class UserFacadeResponseMapper {

    public static UserInfo toUserInfo(User user) {
        return UserInfo.builder()
                       .id(user.getId())
                       .name(user.getName())
                       .point(user.getPoint())
                       .build();
    }
}
