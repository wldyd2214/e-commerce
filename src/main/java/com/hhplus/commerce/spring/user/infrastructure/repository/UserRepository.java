package com.hhplus.commerce.spring.user.infrastructure.repository;

import com.hhplus.commerce.spring.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
