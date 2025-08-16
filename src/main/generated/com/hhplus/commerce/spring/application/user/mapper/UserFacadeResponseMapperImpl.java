package com.hhplus.commerce.spring.application.user.mapper;

import com.hhplus.commerce.spring.old.application.user.dto.UserFacadeResponse;
import com.hhplus.commerce.spring.old.application.user.mapper.UserFacadeResponseMapper;
import com.hhplus.commerce.spring.old.domain.user.dto.UserInfo;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-13T21:03:19+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class UserFacadeResponseMapperImpl implements UserFacadeResponseMapper {

    @Override
    public UserFacadeResponse.PointCharge toUserInfo(UserInfo userInfo) {
        if ( userInfo == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        BigDecimal point = null;

        id = userInfo.getId();
        name = userInfo.getName();
        point = userInfo.getPoint();

        UserFacadeResponse.PointCharge pointCharge = new UserFacadeResponse.PointCharge( id, name, point );

        return pointCharge;
    }
}
