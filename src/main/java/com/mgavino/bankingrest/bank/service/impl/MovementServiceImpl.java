package com.mgavino.bankingrest.bank.service.impl;

import com.mgavino.bankingrest.bank.repository.BankAccountRepository;
import com.mgavino.bankingrest.bank.repository.model.BankAccountEntity;
import com.mgavino.bankingrest.bank.repository.model.MovementEntity;
import com.mgavino.bankingrest.bank.repository.MovementRepository;
import com.mgavino.bankingrest.bank.service.BankAccountService;
import com.mgavino.bankingrest.bank.service.MovementService;
import com.mgavino.bankingrest.bank.service.dto.BankAccountResultDto;
import com.mgavino.bankingrest.bank.service.dto.MovementDto;
import com.mgavino.bankingrest.bank.service.dto.MovementFilterDto;
import com.mgavino.bankingrest.bank.service.dto.MovementResultDto;
import com.mgavino.bankingrest.core.exception.NotEnoughMoneyException;
import com.mgavino.bankingrest.core.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovementServiceImpl implements MovementService {

    @Autowired
    private MovementRepository repository;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private ModelMapper mapper;

    @Transactional
    public MovementResultDto deposit(Long bankId, MovementDto movementDto) throws Exception {

        // convert to positive number
        if (movementDto.getAmount() < 0) {
            movementDto.setAmount(movementDto.getAmount() * -1);
        }
        return insert(bankId, movementDto);

    }

    @Transactional
    public MovementResultDto withdraw(Long bankId, MovementDto movementDto) throws Exception {
        // convert to negative number
        if (movementDto.getAmount() > 0) {
            movementDto.setAmount(movementDto.getAmount() * -1);
        }
        return insert(bankId, movementDto);
    }

    @Transactional
    public MovementResultDto insert(Long bankId, MovementDto movementDto) throws Exception {

        // insert new movement
        MovementEntity movement = mapper.map(movementDto, MovementEntity.class);
        movement.setBankAccountId(bankId);
        MovementEntity savedMovement = repository.save(movement);

        // update bank balance
        bankAccountService.refreshBalance(bankId, movement.getAmount());

        // mapping
        MovementResultDto resultDto = mapper.map(savedMovement, MovementResultDto.class);
        resultDto.setDate(movement.getCreationDate());

        return resultDto;

    }

    public List<MovementResultDto> find(Long bankId, MovementFilterDto movementFilterDto) throws Exception {

        // find list by bankId, dateFrom and dateTo
        List<MovementEntity> movements = repository.findByBankAccountIdAndCreationDateBetween(bankId,
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
