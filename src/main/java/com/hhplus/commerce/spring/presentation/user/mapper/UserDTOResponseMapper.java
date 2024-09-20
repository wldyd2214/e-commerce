package com.hhplus.commerce.spring.presentation.user.mapper;

import com.hhplus.commerce.spring.application.user.dto.UserInfo;
import com.hhplus.commerce.spring.presentation.user.dto.response.UserResponseDTO;

public class UserDTOResponseMapper {
    public static UserResponseDTO toUserResponseDTO(UserInfo userInfo) {
        return UserResponseDTO.builder()
                              .id(userInfo.getId())
                              .name(userInfo.getName())
                              .point(userInfo.getPoint())
                              .build();
    }
}
