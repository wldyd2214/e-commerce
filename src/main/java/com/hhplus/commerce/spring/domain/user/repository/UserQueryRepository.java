package com.hhplus.commerce.spring.domain.user.repository;

import com.hhplus.commerce.spring.domain.user.entity.User;

import java.util.Optional;

public interface UserQueryRepository {

    Optional<User> findById(Long userId);

    Optional<User> findByIdWithLock(Long userId);
}
