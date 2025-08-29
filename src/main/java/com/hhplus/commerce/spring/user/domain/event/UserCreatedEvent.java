package com.hhplus.commerce.spring.user.domain.event;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCreatedEvent {

    private String email;

    @Builder(access = AccessLevel.PRIVATE)
    private UserCreatedEvent(String email) {
        this.email = email;
    }

    public static UserCreatedEvent of(String email) {
        return UserCreatedEvent.builder()
                .email(email)
                .build();
    }
}
