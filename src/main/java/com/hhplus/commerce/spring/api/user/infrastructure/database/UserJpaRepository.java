package com.hhplus.commerce.spring.api.user.infrastructure.database;


import com.hhplus.commerce.spring.api.user.model.User;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    //@Lock(LockModeType.OPTIMISTIC)
    @Query("select u from User u where u.id = :id")
    Optional<User> findByIdWithLock(@Param("id") Long userId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select u from User u where u.id = :id")
    Optional<User> findByIdWithPessimisticLock(@Param("id") Long userId);
}
