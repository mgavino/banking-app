package com.mgavino.bankingrest.bank.service;

import com.mgavino.bankingrest.bank.model.MovementEntity;

import java.util.Date;
import java.util.List;

public interface MovementService {

    public MovementEntity save(MovementEntity entity);

    public List<MovementEntity> find(Long bankId, Date from, Date to);

}
