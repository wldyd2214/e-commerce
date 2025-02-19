package com.hhplus.commerce.spring.application.user.mapper;

import com.hhplus.commerce.spring.application.user.dto.UserFacadeResponse;
import com.hhplus.commerce.spring.domain.user.dto.UserInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserFacadeResponseMapper {

    UserFacadeResponse.PointCharge toUserInfo(UserInfo userInfo);
}
