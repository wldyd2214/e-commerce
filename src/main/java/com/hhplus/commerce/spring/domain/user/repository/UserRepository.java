package com.hhplus.commerce.spring.domain.user.repository;

import com.hhplus.commerce.spring.infrastructure.user.entity.UserEntity;
import com.hhplus.commerce.spring.old.api.user.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> findById(Long userId);
    Optional<User> findByIdWithLock(Long userId);
    Optional<User> findByIdWithPessimisticLock(Long userId);
}
