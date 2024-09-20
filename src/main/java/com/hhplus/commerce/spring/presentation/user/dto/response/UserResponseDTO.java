package com.hhplus.commerce.spring.presentation.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private int point;
}
