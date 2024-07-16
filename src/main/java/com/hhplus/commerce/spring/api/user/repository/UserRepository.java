package com.hhplus.commerce.spring.api.user.repository;

import com.hhplus.commerce.spring.api.user.model.User;
import java.util.Optional;

public interface UserRepository {

    public Optional<User> findById(Long userId);

}
