package com.mgavino.bankingrest.bank.service.impl;

import com.mgavino.bankingrest.bank.repository.AccountRepository;
import com.mgavino.bankingrest.bank.repository.model.AccountEntity;
import com.mgavino.bankingrest.bank.service.AccountService;
import com.mgavino.bankingrest.bank.service.dto.AccountDto;
import com.mgavino.bankingrest.bank.service.dto.AccountFilterDto;
import com.mgavino.bankingrest.bank.service.dto.AccountResultDto;
import com.mgavino.bankingrest.core.exception.NotEnoughMoneyException;
import com.mgavino.bankingrest.core.exception.NotFoundException;
import com.mgavino.bankingrest.user.service.UserService;
import com.mgavino.bankingrest.user.service.dto.UserResultDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @Transactional
    public AccountResultDto insert(AccountDto accountDto) throws Exception {

        // check user exists
        UserResultDto userDto = userService.get(accountDto.getUserId());

        // save bank account
        AccountEntity bankAccount = mapper.map(accountDto, AccountEntity.class);
        AccountEntity savedBankAccount = repository.save(bankAccount);

        // result mapping
        AccountResultDto resultDto = mapper.map(savedBankAccount, AccountResultDto.class);
        return resultDto;

    }

    public List<AccountResultDto> find(AccountFilterDto accountFilterDto) throws Exception {

        // get bank accounts for these filters
        AccountEntity probe = mapper.map(accountFilterDto, AccountEntity.class);
        Example<AccountEntity> example = Example.of(probe);
        List<AccountEntity> bankAccounts = repository.findAll(example);

        if (!bankAccounts.isEmpty()) {

            // mapping
            List<AccountResultDto> resultDtos = bankAccounts.stream()
                    .map( bankAccount -> mapper.map(bankAccount, AccountResultDto.class) )
                    .collect(Collectors.toList());

            return resultDtos;

        } else {
            // throw not found exception if there are no results
            throw new NotFoundException();
        }
    }

    public AccountResultDto get(Long id) throws Exception {
        // get bank account by id
        Optional<AccountEntity> bankAccountOpt = repository.findById(id);

        if (bankAccountOpt.isPresent()) {
            // mapping
            AccountResultDto resultDto = mapper.map(bankAccountOpt.get(), AccountResultDto.class);
            return resultDto;
        } else {
            // throw not found exception if it is not present
            throw new NotFoundException();
        }
    }

    @Transactional
    public AccountResultDto refreshBalance(Long id, Double amount) throws Exception {

        // check bank account exists
        AccountResultDto bankAccountDto = get(id);

        Double newBalance = bankAccountDto.getBalance() + amount;
        if (newBalance > 0) {

            // update bank balance
            Optional<AccountEntity> bankAccountOpt = repository.findById(id);
            AccountEntity bankAccount = bankAccountOpt.get();
            bankAccount.setBalance(newBalance);
            AccountEntity savedBankAccount = repository.save(bankAccount);

            // mapping
            AccountResultDto resultDto = mapper.map(savedBankAccount, AccountResultDto.class);
            return resultDto;

        } else {
            // throw not enough money
            throw new NotEnoughMoneyException();
        }

    }
}
