package com.mgavino.bankingrest.bank.repository;

import com.mgavino.bankingrest.bank.repository.model.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<MovementEntity, Long> {
    public List<MovementEntity> findByAccountAndDateBetween(Long bankId, Date from, Date to);
}
