package com.hhplus.commerce.spring.api.user.infrastructure.database;


import com.hhplus.commerce.spring.api.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {

}
