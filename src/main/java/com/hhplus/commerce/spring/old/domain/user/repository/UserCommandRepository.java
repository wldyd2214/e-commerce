package com.hhplus.commerce.spring.old.domain.user.repository;

import com.hhplus.commerce.spring.old.domain.user.entity.User;

public interface UserCommandRepository {

    User save(User user);
}
