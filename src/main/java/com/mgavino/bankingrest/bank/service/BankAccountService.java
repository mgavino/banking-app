package com.mgavino.bankingrest.bank.service;

import com.mgavino.bankingrest.bank.model.BankAccountEntity;

import java.util.List;

public interface BankAccountService {

    public BankAccountEntity save(BankAccountEntity entity);

    public List<BankAccountEntity> findByUser(Long usrId);

    public BankAccountEntity get(Long id);

}
