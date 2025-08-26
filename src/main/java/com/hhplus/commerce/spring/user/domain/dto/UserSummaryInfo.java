package com.hhplus.commerce.spring.user.domain.dto;

import com.hhplus.commerce.spring.user.domain.entity.User;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UserSummaryInfo {
    private Long id;
    private String email;
    private String name;
    private BigDecimal point;

    public static UserSummaryInfo of(User user) {
        return UserSummaryInfo.builder()
            .id(user.getId())
            .email(user.getEmail())
            .name(user.getName())
            .point(user.getPoint())
            .build();
    }
}
