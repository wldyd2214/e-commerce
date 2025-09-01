package com.hhplus.commerce.spring.user.domain.event;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCreatedEvent {

    private String email;
    private String userName;

    @Builder(access = AccessLevel.PRIVATE)
    private UserCreatedEvent(String email, String userName) {
        this.email = email;
        this.userName = userName;
    }

    public static UserCreatedEvent of(String email, String userName) {
        return UserCreatedEvent.builder()
            .email(email)
            .userName(userName)
            .build();
    }
}
