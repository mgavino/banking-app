package com.mgavino.bankingrest.bank.service.impl;

import com.mgavino.bankingrest.bank.repository.MovementRepository;
import com.mgavino.bankingrest.bank.repository.model.MovementEntity;
import com.mgavino.bankingrest.bank.service.AccountService;
import com.mgavino.bankingrest.bank.service.MovementService;
import com.mgavino.bankingrest.bank.service.dto.MovementDto;
import com.mgavino.bankingrest.bank.service.dto.MovementFilterDto;
import com.mgavino.bankingrest.bank.service.dto.MovementResultDto;
import com.mgavino.bankingrest.bank.service.enums.MovementType;
import com.mgavino.bankingrest.core.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovementServiceImpl implements MovementService {

    @Autowired
    private MovementRepository repository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper mapper;

    @Transactional
    public MovementResultDto insert(Long bankId, MovementType type, MovementDto movementDto) throws Exception {

        // insert new movement
        MovementEntity movement = mapper.map(movementDto, MovementEntity.class);
        movement.setBankAccountId(bankId);
        movement.setAmount(movement.getAmount() * type.getMultiplier());
        MovementEntity savedMovement = repository.save(movement);

        // update bank balance
        accountService.refreshBalance(bankId, movement.getAmount());

        // mapping
        MovementResultDto resultDto = mapper.map(savedMovement, MovementResultDto.class);

        return resultDto;

    }

    public List<MovementResultDto> find(Long bankId, MovementFilterDto movementFilterDto) throws Exception {

        // find list by bankId, dateFrom and dateTo
        List<MovementEntity> movements = repository.findByBankAccountIdAndDateBetween(bankId,
                                            movementFilterDto.getFrom(), movementFilterDto.getTo());
        if (!movements.isEmpty()) {

            // mapping
            List<MovementResultDto> resultDtos = movements.stream()
                    .map(movement -> mapper.map(movement, MovementResultDto.class))
                    .collect(Collectors.toList());
            return resultDtos;

        } else {
            // throw not found exception if there are no results
            throw new NotFoundException();
        }
    }
}
