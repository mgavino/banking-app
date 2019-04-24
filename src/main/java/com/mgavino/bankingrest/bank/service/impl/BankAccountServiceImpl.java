package com.mgavino.bankingrest.bank.service.impl;

import com.mgavino.bankingrest.bank.model.BankAccountEntity;
import com.mgavino.bankingrest.bank.repository.BankAccountRepository;
import com.mgavino.bankingrest.bank.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository repository;

    @Override
    @Transactional(readOnly = false)
    public BankAccountEntity save(BankAccountEntity entity) {
        // TODO: save bank
        return null;
    }

    @Override
    public List<BankAccountEntity> findByUser(Long usrId) {
        // TODO: get banks for this user
        return null;
    }

    @Override
    public BankAccountEntity get(Long id) {
        // TODO: get by bank id
        return null;
    }

}
