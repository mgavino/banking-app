package com.mgavino.bankingrest.bank.repository;

import com.mgavino.bankingrest.bank.repository.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
