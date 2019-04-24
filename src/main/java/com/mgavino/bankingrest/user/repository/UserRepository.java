package com.mgavino.bankingrest.user.repository;

import com.mgavino.bankingrest.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findOptionalByEmail(String email);

}
