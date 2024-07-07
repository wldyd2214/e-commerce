package com.hhplus.commerce.spring.domain.user.repository;

import com.hhplus.commerce.spring.domain.user.User;
import java.util.Optional;

public interface UserRepository {

    public Optional<User> findById(Long userId);

}
