package com.hhplus.commerce.spring.old.presentation.user.mapper;

import com.hhplus.commerce.spring.old.application.user.dto.UserFacadeResponse;
import com.hhplus.commerce.spring.old.presentation.user.dto.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    UserResponse toUserResponse(UserFacadeResponse.PointCharge userInfo);
}
