package com.mgavino.bankingrest.bank.service.impl;

import com.mgavino.bankingrest.bank.model.MovementEntity;
import com.mgavino.bankingrest.bank.repository.BankAccountRepository;
import com.mgavino.bankingrest.bank.repository.MovementRepository;
import com.mgavino.bankingrest.bank.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MovementServiceImpl implements MovementService {

    @Autowired
    private MovementRepository repository;

    @Override
    @Transactional(readOnly = false)
    public MovementEntity save(MovementEntity entity) {
        // TODO: update bank balance
        // TODO: insert new movement
        return null;
    }

    @Override
    public List<MovementEntity> find(Long bankId, Date from, Date to) {
        //TODO: find list by bankId, dateFrom and dateTo
        return null;
    }
}
