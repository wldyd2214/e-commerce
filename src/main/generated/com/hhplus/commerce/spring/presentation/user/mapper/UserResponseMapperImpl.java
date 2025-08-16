package com.hhplus.commerce.spring.presentation.user.mapper;

import com.hhplus.commerce.spring.old.application.user.dto.UserFacadeResponse;
import com.hhplus.commerce.spring.old.presentation.user.dto.response.UserResponse;
import com.hhplus.commerce.spring.old.presentation.user.mapper.UserResponseMapper;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-13T21:03:19+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class UserResponseMapperImpl implements UserResponseMapper {

    @Override
    public UserResponse toUserResponse(UserFacadeResponse.PointCharge userInfo) {
        if ( userInfo == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        BigDecimal point = null;

        id = userInfo.getId();
        name = userInfo.getName();
        point = userInfo.getPoint();

        UserResponse userResponse = new UserResponse( id, name, point );

        return userResponse;
    }
}
