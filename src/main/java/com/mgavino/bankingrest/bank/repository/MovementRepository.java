package com.mgavino.bankingrest.bank.repository;

import com.mgavino.bankingrest.bank.model.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRepository extends JpaRepository<MovementEntity, Long> {
}
