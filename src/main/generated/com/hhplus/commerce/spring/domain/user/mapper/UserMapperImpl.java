package com.hhplus.commerce.spring.domain.user.mapper;

import com.hhplus.commerce.spring.old.domain.user.dto.UserInfo;
import com.hhplus.commerce.spring.old.domain.user.entity.User;
import com.hhplus.commerce.spring.old.domain.user.mapper.UserMapper;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-13T21:03:19+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserInfo toUserInfo(User entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        BigDecimal point = null;

        id = entity.getId();
        name = entity.getName();
        point = entity.getPoint();

        UserInfo userInfo = new UserInfo( id, name, point );

        return userInfo;
    }
}
