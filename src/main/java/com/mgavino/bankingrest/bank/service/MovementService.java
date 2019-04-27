package com.mgavino.bankingrest.bank.service;

import com.mgavino.bankingrest.bank.service.dto.MovementDto;
import com.mgavino.bankingrest.bank.service.dto.MovementFilterDto;
import com.mgavino.bankingrest.bank.service.dto.MovementResultDto;
import com.mgavino.bankingrest.bank.service.enums.MovementType;

import java.util.List;

public interface MovementService {

    public MovementResultDto insert(Long bankId, MovementType type, MovementDto movementDto) throws Exception;

    public List<MovementResultDto> find(Long bankId, MovementFilterDto movementFilterDto) throws Exception;

}
