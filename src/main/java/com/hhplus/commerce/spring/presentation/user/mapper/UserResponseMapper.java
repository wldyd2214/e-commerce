package com.hhplus.commerce.spring.presentation.user.mapper;

import com.hhplus.commerce.spring.application.user.dto.UserFacadeResponse;
import com.hhplus.commerce.spring.presentation.user.dto.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    UserResponse toUserResponse(UserFacadeResponse.PointCharge userInfo);
}
