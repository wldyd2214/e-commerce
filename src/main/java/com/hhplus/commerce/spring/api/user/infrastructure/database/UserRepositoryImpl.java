package com.hhplus.commerce.spring.api.user.infrastructure.database;

import com.hhplus.commerce.spring.api.user.model.User;
import com.hhplus.commerce.spring.api.user.repository.UserRepository;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;

    @Override
    public Optional<User> findById(Long userId) {
        return jpaRepository.findById(userId);
    }

    @Override
    public Optional<User> findByIdWithLock(Long userId) {
        return jpaRepository.findByIdWithLock(userId);
    }

    @Override
    public Optional<User> findByIdWithPessimisticLock(Long userId) {
        return jpaRepository.findByIdWithPessimisticLock(userId);
    }
}
