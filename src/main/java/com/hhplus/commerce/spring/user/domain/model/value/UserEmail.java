package com.hhplus.commerce.spring.user.domain.model.value;

import com.hhplus.commerce.spring.common.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.common.exception.code.BadRequestErrorCode;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEmail {

    private static final Pattern EMAIL_PATTERN =
        Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

    private String address;

    @Builder(access = AccessLevel.PRIVATE)
    private UserEmail(String address) {
        this.address = address;
    }

    public static UserEmail create(String address) {
        validateEmail(address);
        return UserEmail.builder()
                        .address(address)
                        .build();
    }

    private static void validateEmail(String address) {
        if (!EMAIL_PATTERN.matcher(address).matches()) {
            throw new CustomBadRequestException(BadRequestErrorCode.INVALID_EMAIL_FORMAT);
        }
    }
}
