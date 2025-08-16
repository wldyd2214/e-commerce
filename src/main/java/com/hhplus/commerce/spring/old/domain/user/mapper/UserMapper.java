package com.hhplus.commerce.spring.old.domain.user.mapper;

import com.hhplus.commerce.spring.old.domain.user.dto.UserInfo;
import com.hhplus.commerce.spring.old.domain.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserInfo toUserInfo(User entity);
}
