package com.mgavino.bankingrest.bank.service;

import com.mgavino.bankingrest.bank.service.dto.AccountDto;
import com.mgavino.bankingrest.bank.service.dto.AccountFilterDto;
import com.mgavino.bankingrest.bank.service.dto.AccountResultDto;

import java.util.List;

public interface AccountService {

    public AccountResultDto insert(AccountDto accountDto) throws Exception;

    public List<AccountResultDto> find(AccountFilterDto accountFilterDto) throws Exception;

    public AccountResultDto get(Long id) throws Exception;

    public AccountResultDto refreshBalance(Long id, Double amount) throws Exception;

}
