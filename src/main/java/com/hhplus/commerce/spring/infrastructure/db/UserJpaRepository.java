package com.hhplus.commerce.spring.infrastructure.db;


import com.hhplus.commerce.spring.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {

}
