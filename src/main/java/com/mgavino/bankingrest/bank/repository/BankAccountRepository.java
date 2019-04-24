package com.mgavino.bankingrest.bank.repository;

import com.mgavino.bankingrest.bank.model.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {
}
