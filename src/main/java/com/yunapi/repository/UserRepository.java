package com.yunapi.repository;

import com.yunapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> , JpaSpecificationExecutor<User> {
    Optional<User> findTopByEmail(String data);

    Optional<User> findTopByCi(String ci);

    Optional<User> findTopBySecurityCodeAndEmail(String encSHA256, String email);

    Optional<User> findByIdAndTokenKey(long id, String token);
}
