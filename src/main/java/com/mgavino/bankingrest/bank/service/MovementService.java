package com.mgavino.bankingrest.bank.service;

import com.mgavino.bankingrest.bank.repository.model.MovementEntity;
import com.mgavino.bankingrest.bank.service.dto.MovementDto;
import com.mgavino.bankingrest.bank.service.dto.MovementFilterDto;
import com.mgavino.bankingrest.bank.service.dto.MovementResultDto;

import java.util.Date;
import java.util.List;

public interface MovementService {

    public MovementResultDto deposit(Long bankId, MovementDto movementDto) throws Exception;

    public MovementResultDto withdraw(Long bankId, MovementDto movementDto) throws Exception;

    public MovementResultDto insert(Long bankId, MovementDto movementDto) throws Exception;

    public List<MovementResultDto> find(Long bankId, MovementFilterDto movementFilterDto) throws Exception;

}
