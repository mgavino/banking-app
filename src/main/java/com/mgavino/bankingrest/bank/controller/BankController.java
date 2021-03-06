package com.mgavino.bankingrest.bank.controller;

import com.mgavino.bankingrest.bank.service.AccountService;
import com.mgavino.bankingrest.bank.service.MovementService;
import com.mgavino.bankingrest.bank.service.dto.*;
import com.mgavino.bankingrest.bank.service.enums.MovementType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MovementService movementService;

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<AccountResultDto> post(@RequestBody @Valid AccountDto bankAccount, UriComponentsBuilder ucBuilder) throws Exception {
        // insert bank account
        AccountResultDto result = accountService.insert(bankAccount);

        // build response
        URI location = ucBuilder.path("/bank/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).body(result);

    }

    @RequestMapping(method=RequestMethod.GET)
    public List<AccountResultDto> getAll(@ModelAttribute @Valid AccountFilterDto filter) throws Exception {
        // get bank accounts by filter
        return accountService.find(filter);
    }

    @RequestMapping(value="/{bankId}", method=RequestMethod.GET)
    public AccountResultDto get(@PathVariable("bankId") Long bankId) throws Exception {
        // get bank by id
        return accountService.get(bankId);
    }

    @RequestMapping(value="/{bankId}/movements", method=RequestMethod.GET)
    public List<MovementResultDto> movements(@PathVariable("bankId") Long bankId, @ModelAttribute @Valid MovementFilterDto filter) throws Exception {
        // get movements by filter
        return movementService.find(bankId, filter);
    }

    @RequestMapping(value="/{bankId}/deposit", method=RequestMethod.POST)
    public MovementResultDto deposit(@PathVariable("bankId") Long bankId, @RequestBody @Valid MovementDto movement) throws Exception {
        // insert deposit movement
        return movementService.insert(bankId, MovementType.DEPOSIT, movement);
    }

    @RequestMapping(value="/{bankId}/withdraw", method=RequestMethod.POST)
    public MovementResultDto withdraw(@PathVariable("bankId") Long bankId, @RequestBody @Valid MovementDto movement) throws Exception {
        // insert withdraw movement
        return movementService.insert(bankId, MovementType.WITHDRAW, movement);
    }

}
