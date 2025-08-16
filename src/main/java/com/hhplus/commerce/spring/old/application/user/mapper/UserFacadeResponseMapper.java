package com.hhplus.commerce.spring.old.application.user.mapper;

import com.hhplus.commerce.spring.old.application.user.dto.UserFacadeResponse;
import com.hhplus.commerce.spring.old.domain.user.dto.UserInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserFacadeResponseMapper {

    UserFacadeResponse.PointCharge toUserInfo(UserInfo userInfo);
}
