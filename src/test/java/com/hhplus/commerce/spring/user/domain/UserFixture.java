package com.hhplus.commerce.spring.user.domain;

import com.hhplus.commerce.spring.user.domain.command.UserCommand;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserFixture {

    public static UserCommand.Register createUserRegisterCommand() {
        return UserCommand.Register.of("jypark@commerce.app", "박지용", "verysecret");
    }

    public static PasswordEncoder createPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
