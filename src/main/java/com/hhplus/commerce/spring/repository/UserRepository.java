package com.hhplus.commerce.spring.repository;

import com.hhplus.commerce.spring.model.entity.User;
import java.util.Optional;

public interface UserRepository {

    public Optional<User> findById(Long userId);

}
