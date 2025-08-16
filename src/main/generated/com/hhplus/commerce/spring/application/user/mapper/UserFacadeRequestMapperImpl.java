package com.hhplus.commerce.spring.application.user.mapper;

import com.hhplus.commerce.spring.old.application.user.dto.UserFacadeRequest;
import com.hhplus.commerce.spring.old.application.user.mapper.UserFacadeRequestMapper;
import com.hhplus.commerce.spring.old.domain.payment.dto.PaymentCommand;
import com.hhplus.commerce.spring.old.domain.user.dto.UserCommand;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-13T21:03:19+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class UserFacadeRequestMapperImpl implements UserFacadeRequestMapper {

    @Override
    public PaymentCommand.Payment toPaymentCommand(UserFacadeRequest.PointCharge pointCharge) {
        if ( pointCharge == null ) {
            return null;
        }

        BigDecimal amount = null;
        Long userId = null;

        amount = pointCharge.getPoint();
        userId = pointCharge.getUserId();

        PaymentCommand.Payment payment = new PaymentCommand.Payment( userId, amount );

        return payment;
    }

    @Override
    public UserCommand.PointCharge toUserCommand(UserFacadeRequest.PointCharge pointCharge) {
        if ( pointCharge == null ) {
            return null;
        }

        BigDecimal chargePoint = null;
        Long userId = null;

        chargePoint = pointCharge.getPoint();
        userId = pointCharge.getUserId();

        UserCommand.PointCharge pointCharge1 = new UserCommand.PointCharge( userId, chargePoint );

        return pointCharge1;
    }
}
