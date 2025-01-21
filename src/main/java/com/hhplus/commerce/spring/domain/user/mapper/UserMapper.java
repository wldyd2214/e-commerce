package com.hhplus.commerce.spring.domain.user.mapper;

import com.hhplus.commerce.spring.domain.user.entity.UserEntity;
import com.hhplus.commerce.spring.domain.user.dto.User;

/**
 * 클린 아키텍처에서는 Mapper를 사용하여 엔티티와 도메인 객체 간의 변환을 처리하는 것이 좋습니다. 이렇게 하면 책임의 분리와 유지보수성이 강화됩니다.
 * 도메인 객체는 비즈니스 로직에 집중하고, Mapper는 데이터 변환에 집중하도록 설계하는 것이 클린 아키텍처의 원칙에 부합합니다.
 */

public class UserMapper {

    public static User toUser(UserEntity entity) {
        return User.builder()
                   .id(entity.getId())
                   .name(entity.getName())
                   .point(entity.getPoint())
                   .build();
    }
}
