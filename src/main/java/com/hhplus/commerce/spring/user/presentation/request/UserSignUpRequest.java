package com.hhplus.commerce.spring.user.presentation.request;

import com.hhplus.commerce.spring.user.domain.command.UserCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSignUpRequest {

    private String email;
    private String name;
    private String password;

    public UserCommand.Register toCommand() {
        return UserCommand.Register.of(email, name, password);
    }
}
