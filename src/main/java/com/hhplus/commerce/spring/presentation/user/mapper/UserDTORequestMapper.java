package com.hhplus.commerce.spring.presentation.user.mapper;

import com.hhplus.commerce.spring.application.user.dto.UserFacadeRequest;

import java.lang.annotation.Target;
import java.math.BigDecimal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserDTORequestMapper {

    UserDTORequestMapper INSTANCE = Mappers.getMapper(UserDTORequestMapper.class);

    @Mapping(source = "chargePoint", target = "point")
    UserFacadeRequest.PointCharge toPointCharge(Long userId, BigDecimal chargePoint);
}
