package com.hhplus.commerce.spring.application.user.mapper;

import com.hhplus.commerce.spring.application.user.dto.UserFacadeRequest;
import com.hhplus.commerce.spring.domain.payment.dto.PaymentCommand;
import com.hhplus.commerce.spring.domain.user.dto.UserCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserFacadeRequestMapper {

    @Mapping(source = "point", target = "amount")
    PaymentCommand.Payment toPaymentCommand(UserFacadeRequest.PointCharge pointCharge);

    @Mapping(source = "point", target = "chargePoint")
    UserCommand.PointCharge toUserCommand(UserFacadeRequest.PointCharge pointCharge);
}
