package com.mgavino.bankingrest.bank.service;

import com.mgavino.bankingrest.bank.service.dto.AccountDto;
import com.mgavino.bankingrest.bank.service.dto.AccountFilterDto;
import com.mgavino.bankingrest.bank.service.dto.AccountResultDto;

import java.util.List;

public interface AccountService {

    /**
     * Create new Bank Account
     * @param accountDto
     * @return
     * @throws Exception
     */
    public AccountResultDto insert(AccountDto accountDto) throws Exception;

    /**
     * Find Bank Accounts by filter
     * @param accountFilterDto
     * @return
     * @throws Exception
     */
    public List<AccountResultDto> find(AccountFilterDto accountFilterDto) throws Exception;

    /**
     * Find Bank Account by ID
     * @param id
     * @return
     * @throws Exception
     */
    public AccountResultDto get(Long id) throws Exception;

    /**
     * Add or subtract amount from the Bank Account balance of the given id
     * @param id
     * @param amount
     * @return
     * @throws Exception
     */
    public AccountResultDto refreshBalance(Long id, Double amount) throws Exception;

}
