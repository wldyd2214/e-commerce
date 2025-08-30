package com.hhplus.commerce.spring.user.domain.dto;

import com.hhplus.commerce.spring.user.domain.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UserSummaryInfo {

    @Schema(description = "유니크키", example = "101")
    @NotNull
    private Long id;

    @Schema(description = "이메일", example = "jypark@gmail.com")
    @NotNull
    private String email;

    @Schema(description = "이름", example = "박지용")
    @NotNull
    private String name;

    @Schema(description = "잔여 포인트", example = "7000")
    @NotNull
    private BigDecimal point;

    public static UserSummaryInfo of(User user) {
        return UserSummaryInfo.builder()
            .id(user.getId())
            .email(user.getEmail().getAddress())
            .name(user.getName())
            .point(user.getPoint())
            .build();
    }
}
