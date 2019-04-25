package com.mgavino.bankingrest.bank.service;

import com.mgavino.bankingrest.bank.service.dto.BankAccountDto;
import com.mgavino.bankingrest.bank.service.dto.BankAccountFilterDto;
import com.mgavino.bankingrest.bank.service.dto.BankAccountResultDto;

import java.util.List;

public interface BankAccountService {

    public BankAccountResultDto insert(BankAccountDto bankAccountDto) throws Exception;

    public List<BankAccountResultDto> find(BankAccountFilterDto bankAccountFilterDto) throws Exception;

    public BankAccountResultDto get(Long id) throws Exception;

    public BankAccountResultDto refreshBalance(Long id, Double amount) throws Exception;

}
