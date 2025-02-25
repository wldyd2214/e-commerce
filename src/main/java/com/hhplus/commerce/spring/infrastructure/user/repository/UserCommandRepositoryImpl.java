package com.hhplus.commerce.spring.infrastructure.user.repository;

import com.hhplus.commerce.spring.domain.user.entity.User;
import com.hhplus.commerce.spring.domain.user.repository.UserCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserCommandRepositoryImpl implements UserCommandRepository {

    private final UserJpaRepository jpaRepository;

    @Override
    public User save(User user) {
        return jpaRepository.save(user);
    }
}
