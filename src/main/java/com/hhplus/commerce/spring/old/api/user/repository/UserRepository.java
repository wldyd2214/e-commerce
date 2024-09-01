package com.hhplus.commerce.spring.old.api.user.repository;

import com.hhplus.commerce.spring.old.api.user.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long userId);
    Optional<User> findByIdWithLock(Long userId);
    Optional<User> findByIdWithPessimisticLock(Long userId);
}
