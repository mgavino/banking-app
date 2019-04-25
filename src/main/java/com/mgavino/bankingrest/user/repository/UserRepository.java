package com.mgavino.bankingrest.user.repository;

import com.mgavino.bankingrest.user.repository.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public int countByEmail(String email);
}
