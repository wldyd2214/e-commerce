package com.hhplus.commerce.spring.domain.user.repository;


import com.hhplus.commerce.spring.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {

}
