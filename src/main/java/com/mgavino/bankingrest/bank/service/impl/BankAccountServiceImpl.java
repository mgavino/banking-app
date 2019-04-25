package com.mgavino.bankingrest.bank.service.impl;

import com.mgavino.bankingrest.bank.repository.model.BankAccountEntity;
import com.mgavino.bankingrest.bank.repository.BankAccountRepository;
import com.mgavino.bankingrest.bank.service.BankAccountService;
import com.mgavino.bankingrest.bank.service.dto.BankAccountDto;
import com.mgavino.bankingrest.bank.service.dto.BankAccountFilterDto;
import com.mgavino.bankingrest.bank.service.dto.BankAccountResultDto;
import com.mgavino.bankingrest.core.exception.NotEnoughMoneyException;
import com.mgavino.bankingrest.core.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Transactional
    public BankAccountResultDto insert(BankAccountDto bankAccountDto) throws Exception {

        // save bank account
        BankAccountEntity bankAccount = mapper.map(bankAccountDto, BankAccountEntity.class);
        // TODO: generate number
        BankAccountEntity savedBankAccount = repository.save(bankAccount);

        // result mapping
        BankAccountResultDto resultDto = mapper.map(savedBankAccount, BankAccountResultDto.class);
        return resultDto;

    }

    public List<BankAccountResultDto> find(BankAccountFilterDto bankAccountFilterDto) throws Exception {

        // get bank accounts for these filters
        BankAccountEntity probe = mapper.map(bankAccountFilterDto, BankAccountEntity.class);
        Example<BankAccountEntity> example = Example.of(probe);
        List<BankAccountEntity> bankAccounts = repository.findAll(example);

        if (!bankAccounts.isEmpty()) {

            // mapping
            List<BankAccountResultDto> resultDtos = bankAccounts.stream()
                    .map( bankAccount -> mapper.map(bankAccount, BankAccountResultDto.class) )
                    .collect(Collectors.toList());

            return resultDtos;

        } else {
            // throw not found exception if there are no results
            throw new NotFoundException();
        }
    }

    public BankAccountResultDto get(Long id) throws Exception {
        // get bank account by id
        Optional<BankAccountEntity> bankAccountOpt = repository.findById(id);

        if (bankAccountOpt.isPresent()) {
            // mapping
            BankAccountResultDto resultDto = mapper.map(bankAccountOpt.get(), BankAccountResultDto.class);
            return resultDto;
        } else {
            // throw not found exception if it is not present
            throw new NotFoundException();
        }
    }

    @Transactional
    public BankAccountResultDto refreshBalance(Long id, Double amount) throws Exception {

        Optional<BankAccountEntity> bankAccountOpt = repository.findById(id);
        if (bankAccountOpt.isPresent()) {

            BankAccountEntity bankAccount = bankAccountOpt.get();
            Double newBalance = bankAccount.getBalance() + amount;
            if (newBalance > 0) {

                // update bank balance
                bankAccount.setBalance(newBalance);
                BankAccountEntity savedBankAccount = repository.save(bankAccount);

                // mapping
                BankAccountResultDto resultDto = mapper.map(savedBankAccount, BankAccountResultDto.class);
                return resultDto;

            } else {
                // throw not enough money
                throw new NotEnoughMoneyException();
            }

        } else {
            // throw not found exception if it is not present
            throw new NotFoundException();
        }

    }
}
