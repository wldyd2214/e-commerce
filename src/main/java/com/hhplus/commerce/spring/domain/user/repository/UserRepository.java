package com.hhplus.commerce.spring.domain.user.repository;

import com.hhplus.commerce.spring.infrastructure.user.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> findById(Long userId);
}
