package com.hhplus.commerce.spring.user.presentation.request;

import com.hhplus.commerce.spring.user.domain.command.UserCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserSignUpRequest {

    @Schema(description = "이메일", example = "jypark@gmail.com")
    @NotBlank
    private String email;

    @Schema(description = "이름", example = "박지용")
    @NotBlank
    private String name;

    @Schema(description = "비밀번호", example = "1q2w3e4r!!")
    @NotBlank
    private String password;

    public UserCommand.Register toCommand() {
        return UserCommand.Register.of(email, name, password);
    }
}
