package com.hhplus.commerce.spring.infrastructure.user.database;

import com.hhplus.commerce.spring.infrastructure.user.entity.UserEntity;
import com.hhplus.commerce.spring.old.api.user.model.User;
import com.hhplus.commerce.spring.domain.user.repository.UserRepository;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;

    @Override
    public  Optional<UserEntity> findById(Long userId) { return jpaRepository.findById(userId); }
}
