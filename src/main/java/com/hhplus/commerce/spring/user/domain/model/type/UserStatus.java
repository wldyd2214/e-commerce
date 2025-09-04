package com.hhplus.commerce.spring.user.domain.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    ACTIVE("등록 완료"),
    DEACTIVATE("탈퇴");

    private final String description;
}
