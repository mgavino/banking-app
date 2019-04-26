package com.mgavino.bankingrest.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgavino.bankingrest.bank.service.AccountService;
import com.mgavino.bankingrest.bank.service.MovementService;
import com.mgavino.bankingrest.bank.service.dto.AccountDto;
import com.mgavino.bankingrest.bank.service.dto.MovementDto;
import com.mgavino.bankingrest.bank.service.enums.MovementType;
import com.mgavino.bankingrest.core.exception.NotEnoughMoneyException;
import com.mgavino.bankingrest.core.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BankControllerFailTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MovementService movementService;
    @MockBean
    private AccountService accountService;

    @Test
    public void postBadRequest() throws Exception {

        // try create bank account
        AccountDto bank = new AccountDto();
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post( "/bank-account" )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bank)));

        // check 400 (valid parameters)
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void getAllNotFound() throws Exception {

        // mock service
        Mockito.when(accountService.find(Mockito.any()))
                .thenThrow(new NotFoundException());

        // try get bank accounts
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get( "/bank-account" )
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("userId", "1"));

        // check 401
        result.andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void getAllBadRequest() throws Exception {

        // try get bank accounts
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get( "/bank-account" )
                        .contentType(MediaType.APPLICATION_JSON));

        // check 400 (valid parameters)
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void getNotFound() throws Exception {

        // mock service
        Mockito.when(accountService.get(Mockito.any()))
                .thenThrow(new NotFoundException());

        // try get bank account
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get( "/bank-account/1" )
                        .contentType(MediaType.APPLICATION_JSON));

        // check 401 (valid parameters)
        result.andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void movementsNotFound() throws Exception {

        // mock service
        Mockito.when(movementService.find(Mockito.anyLong(), Mockito.any()))
                .thenThrow(new NotFoundException());

        // try get movements
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get( "/bank-account/1/movements" )
                        .contentType(MediaType.APPLICATION_JSON));

        // check 401
        result.andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    public void withdrawNotFound() throws Exception {

        // mock service
        Mockito.when(movementService.insert(Mockito.anyLong(), MovementType.DEPOSIT, Mockito.any()))
                .thenThrow(new NotFoundException());

        // try create withdraw
        MovementDto movement = new MovementDto();
        movement.setAmount(20.0);
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post( "/bank-account/1/withdraw" )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movement)));

        // check 401
        result.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void withdrawNotEnoughMoney() throws Exception {

        // mock service
        Mockito.when(movementService.insert(Mockito.anyLong(), MovementType.WITHDRAW, Mockito.any()))
                .thenThrow(new NotEnoughMoneyException());

        // try create withdraw
        MovementDto movement = new MovementDto();
        movement.setAmount(20.0);
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post( "/bank-account/1/withdraw" )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movement)));

        // check 400 (enough money)
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void withdrawBadRequest() throws Exception {

        // try create withdraw
        MovementDto movement = new MovementDto();
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post( "/bank-account/1/withdraw" )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movement)));

        // check 400 (valid parameters)
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void depositBadRequest() throws Exception {

        // try create deposit
        MovementDto movement = new MovementDto();
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post( "/bank-account/1/deposit" )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movement)));

        // check 400 (valid parameters)
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

}
