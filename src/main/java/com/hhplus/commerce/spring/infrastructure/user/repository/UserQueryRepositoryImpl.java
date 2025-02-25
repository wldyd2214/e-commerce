package com.hhplus.commerce.spring.infrastructure.user.repository;

import com.hhplus.commerce.spring.domain.user.entity.User;
import com.hhplus.commerce.spring.domain.user.repository.UserQueryRepository;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserQueryRepositoryImpl implements UserQueryRepository {

    private final UserJpaRepository jpaRepository;

    @Override
    public  Optional<User> findById(Long userId) { return jpaRepository.findById(userId); }

    @Override
    public Optional<User> findByIdWithLock(Long userId) {
        return jpaRepository.findByIdWithLock(userId);
    }
}
