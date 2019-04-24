package com.mgavino.bankingrest.bank.controller;

import com.mgavino.bankingrest.bank.model.BankAccountEntity;
import com.mgavino.bankingrest.bank.model.MovementEntity;
import com.mgavino.bankingrest.bank.service.BankAccountService;
import com.mgavino.bankingrest.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.List;

@RestController("/bank-account")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<BankAccountEntity> save(@RequestBody BankAccountEntity entity) {
        // TODO: save bank
        return null;
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<BankAccountEntity>> getAll(@RequestParam("userId") Long userId) {
        // TODO: get banks by users
        return null;
    }

    @RequestMapping(value="/{bankId}", method=RequestMethod.GET)
    public ResponseEntity<BankAccountEntity> get(@PathVariable("bankId") Long bankId) {
        // TODO: get bank
        return null;
    }

    @RequestMapping(value="/{bankId}/movements/{dateFrom}/{dateTo}")
    public ResponseEntity<List<MovementEntity>> movements(@PathVariable("dateFrom") Date dateFrom, @PathVariable("dateTo") Date dateTo) {
        // TODO: get movements
        return null;
    }

    @RequestMapping(value="/{bankId}/deposit", method=RequestMethod.POST)
    public ResponseEntity<MovementEntity> deposit(@PathVariable("bankId") Long bankId, @RequestParam("amount") Double amount) {
        // TODO: save deposit movement
        return null;
    }

    @RequestMapping(value="/{bankId}/withdraw", method=RequestMethod.POST)
    public ResponseEntity<MovementEntity> withdraw(@PathVariable("bankId") Long bankId, @RequestParam("amount") Double amount) {
        // TODO: save deposit movement
        return null;
    }



}
