package com.hhplus.commerce.spring.infrastructure.user.repository;


import com.hhplus.commerce.spring.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
