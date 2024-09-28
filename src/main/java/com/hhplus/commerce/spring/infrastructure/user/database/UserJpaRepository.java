package com.hhplus.commerce.spring.infrastructure.user.database;


import com.hhplus.commerce.spring.infrastructure.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
