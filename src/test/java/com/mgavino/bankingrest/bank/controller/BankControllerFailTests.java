package com.mgavino.bankingrest.bank.controller;

import com.mgavino.bankingrest.bank.service.AccountService;
import com.mgavino.bankingrest.bank.service.MovementService;
import com.mgavino.bankingrest.bank.service.dto.AccountDto;
import com.mgavino.bankingrest.bank.service.dto.MovementDto;
import com.mgavino.bankingrest.bank.service.enums.MovementType;
import com.mgavino.bankingrest.core.GenericControllerTests;
import com.mgavino.bankingrest.core.exception.NotEnoughMoneyException;
import com.mgavino.bankingrest.core.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BankControllerFailTests extends GenericControllerTests {

    private static final String URI = "/bank";

    @MockBean
    private MovementService movementService;
    @MockBean
    private AccountService accountService;

    @Test
    public void postBadRequest() throws Exception {

        // try create bank account
        AccountDto bank = new AccountDto();
        ResultActions result = post(URI, bank);

        // check 400 (invalid parameters)
        checkStatus(result, HttpStatus.BAD_REQUEST);

    }

    @Test
    public void getAllNotFound() throws Exception {

        // mock not found
        Mockito.when(accountService.find(Mockito.any())).thenThrow(new NotFoundException());

        // try get bank accounts
        Map<String, String> params = new HashMap<>();
        params.put("user", "1");
        ResultActions result = get(URI, params);

        // check 404 (not found)
        checkStatus(result, HttpStatus.NOT_FOUND);

    }

    @Test
    public void getAllBadRequest() throws Exception {

        // try get bank accounts
        ResultActions result = get(URI);

        // check 400 (invalid parameters)
        checkStatus(result, HttpStatus.BAD_REQUEST);

    }

    @Test
    public void getNotFound() throws Exception {

        // mock service
        Mockito.when(accountService.get(Mockito.eq(1L))).thenThrow(new NotFoundException());

        // try get bank account
        ResultActions result = get(URI + "/1");

        // check 404 (not found)
        checkStatus(result, HttpStatus.NOT_FOUND);

    }

    @Test
    public void movementsNotFound() throws Exception {

        // mock service
        Mockito.when(movementService.find(Mockito.eq(1L), Mockito.any())).thenThrow(new NotFoundException());

        // try get movements
        Map<String, String> params = new HashMap<>();
        params.put("from", "2019-04-26");
        params.put("to", "2019-04-28");
        ResultActions result = get(URI + "/1/movements", params);

        // check 404 (not found)
        checkStatus(result, HttpStatus.NOT_FOUND);

    }

    @Test
    public void withdrawNotFound() throws Exception {

        // mock service
        Mockito.when(movementService.insert(Mockito.eq(1L), Mockito.eq(MovementType.WITHDRAW), Mockito.any()))
                .thenThrow(new NotFoundException());

        // try create withdraw
        MovementDto movement = new MovementDto();
        movement.setAmount(20.0);
        ResultActions result = post(URI + "/1/withdraw", movement);

        // check 404 (not found)
        checkStatus(result, HttpStatus.NOT_FOUND);

    }

    @Test
    public void withdrawNotEnoughMoney() throws Exception {

        // mock service
        Mockito.when(movementService.insert(Mockito.eq(1L), Mockito.eq(MovementType.WITHDRAW), Mockito.any()))
                .thenThrow(new NotEnoughMoneyException());

        // try create withdraw
        MovementDto movement = new MovementDto();
        movement.setAmount(20.0);
        ResultActions result = post(URI + "/1/withdraw", movement);

        // check 400 (enough money)
        checkStatus(result, HttpStatus.BAD_REQUEST);

    }

    @Test
    public void withdrawBadRequest() throws Exception {

        // try create withdraw
        MovementDto movement = new MovementDto();
        movement.setAmount(-20.0);
        ResultActions result = post(URI + "/1/withdraw", movement);

        // check 400 (invalid parameters)
        checkStatus(result, HttpStatus.BAD_REQUEST);

    }

    @Test
    public void depositNotFound() throws Exception {

        // mock service
        Mockito.when(movementService.insert(Mockito.eq(1L), Mockito.eq(MovementType.DEPOSIT), Mockito.any()))
                .thenThrow(new NotFoundException());

        // try create withdraw
        MovementDto movement = new MovementDto();
        movement.setAmount(20.0);
        ResultActions result = post(URI + "/1/deposit", movement);

        // check 404 (not found)
        checkStatus(result, HttpStatus.NOT_FOUND);

    }

    @Test
    public void depositBadRequest() throws Exception {

        // try create deposit
        MovementDto movement = new MovementDto();
        movement.setAmount(-20.0);
        ResultActions result = post(URI + "/1/deposit", movement);

        // check 400 (invalid parameters)
        checkStatus(result, HttpStatus.BAD_REQUEST);

    }

}
