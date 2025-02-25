package com.hhplus.commerce.spring.domain.user.repository;

import com.hhplus.commerce.spring.domain.user.entity.User;

public interface UserCommandRepository {

    User save(User user);
}
