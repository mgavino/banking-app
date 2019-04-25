package com.mgavino.bankingrest.bank.controller;

import com.mgavino.bankingrest.bank.repository.model.BankAccountEntity;
import com.mgavino.bankingrest.bank.repository.model.MovementEntity;
import com.mgavino.bankingrest.bank.service.BankAccountService;
import com.mgavino.bankingrest.bank.service.MovementService;
import com.mgavino.bankingrest.bank.service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController("/bank-account")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private MovementService movementService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method=RequestMethod.POST)
    public BankAccountResultDto post(@RequestBody BankAccountDto bankAccount) throws Exception {
        // insert bank account
        return bankAccountService.insert(bankAccount);
    }

    @RequestMapping(method=RequestMethod.GET)
    public List<BankAccountResultDto> getAll(@ModelAttribute BankAccountFilterDto filter) throws Exception {
        // get bank accounts by filter
        return bankAccountService.find(filter);
    }

    @RequestMapping(value="/{bankId}", method=RequestMethod.GET)
    public BankAccountResultDto get(@PathVariable("bankId") Long bankId) throws Exception {
        // get bank by id
        return bankAccountService.get(bankId);
    }

    @RequestMapping(value="/{bankId}/movements")
    public List<MovementResultDto> movements(@PathVariable("bankId") Long bankId, @ModelAttribute MovementFilterDto filter) throws Exception {
        // get movements by filter
        return movementService.find(bankId, filter);
    }

    @RequestMapping(value="/{bankId}/deposit", method=RequestMethod.POST)
    public MovementResultDto deposit(@PathVariable("bankId") Long bankId, @RequestBody MovementDto movement) throws Exception {
        // insert deposit movement
        return movementService.deposit(bankId, movement);
    }

    @RequestMapping(value="/{bankId}/withdraw", method=RequestMethod.POST)
    public MovementResultDto withdraw(@PathVariable("bankId") Long bankId, @RequestBody MovementDto movement) throws Exception {
        // insert withdraw movement
        return movementService.withdraw(bankId, movement);
    }

}
