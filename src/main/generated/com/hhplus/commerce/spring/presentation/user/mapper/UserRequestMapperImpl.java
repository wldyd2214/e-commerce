package com.hhplus.commerce.spring.presentation.user.mapper;

import com.hhplus.commerce.spring.old.application.user.dto.UserFacadeRequest;
import com.hhplus.commerce.spring.old.presentation.user.mapper.UserRequestMapper;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-13T21:03:19+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class UserRequestMapperImpl implements UserRequestMapper {

    @Override
    public UserFacadeRequest.PointCharge toPointCharge(Long userId, BigDecimal chargePoint) {
        if ( userId == null && chargePoint == null ) {
            return null;
        }

        Long userId1 = null;
        userId1 = userId;
        BigDecimal point = null;
        point = chargePoint;

        UserFacadeRequest.PointCharge pointCharge = new UserFacadeRequest.PointCharge( userId1, point );

        return pointCharge;
    }
}
