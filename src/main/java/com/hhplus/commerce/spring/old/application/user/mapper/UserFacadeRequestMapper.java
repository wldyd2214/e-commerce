package com.hhplus.commerce.spring.old.application.user.mapper;

import com.hhplus.commerce.spring.old.application.user.dto.UserFacadeRequest;
import com.hhplus.commerce.spring.old.domain.payment.dto.PaymentCommand;
import com.hhplus.commerce.spring.old.domain.user.dto.UserCommand.PointCharge;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserFacadeRequestMapper {

    @Mapping(source = "point", target = "amount")
    PaymentCommand.Payment toPaymentCommand(UserFacadeRequest.PointCharge pointCharge);

    @Mapping(source = "point", target = "chargePoint")
    PointCharge toUserCommand(UserFacadeRequest.PointCharge pointCharge);
}
