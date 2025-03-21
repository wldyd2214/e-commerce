package com.hhplus.commerce.spring.presentation.user.mapper;

import com.hhplus.commerce.spring.application.user.dto.UserFacadeRequest;

import java.math.BigDecimal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {

    @Mapping(source = "chargePoint", target = "point")
    UserFacadeRequest.PointCharge toPointCharge(Long userId, BigDecimal chargePoint);
}
