package com.mgavino.bankingrest.bank.service;

import com.mgavino.bankingrest.bank.service.dto.MovementDto;
import com.mgavino.bankingrest.bank.service.dto.MovementFilterDto;
import com.mgavino.bankingrest.bank.service.dto.MovementResultDto;
import com.mgavino.bankingrest.bank.service.enums.MovementType;

import java.util.List;

public interface MovementService {

    /**
     * Create new Bank Movement for the given Bank Account ID.
     * The amount of the Movement will be positive or negative depending on the given Movement Type
     * @param bankId
     * @param type
     * @param movementDto
     * @return
     * @throws Exception
     */
    public MovementResultDto insert(Long bankId, MovementType type, MovementDto movementDto) throws Exception;

    /**
     * Find Bank Movements by filter for the given Bank Account ID
     * @param bankId
     * @param movementFilterDto
     * @return
     * @throws Exception
     */
    public List<MovementResultDto> find(Long bankId, MovementFilterDto movementFilterDto) throws Exception;

}
