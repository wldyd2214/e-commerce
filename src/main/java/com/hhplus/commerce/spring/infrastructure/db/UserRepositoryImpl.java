package com.hhplus.commerce.spring.infrastructure.db;

import com.hhplus.commerce.spring.model.User;
import com.hhplus.commerce.spring.repository.UserRepository;
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
}
