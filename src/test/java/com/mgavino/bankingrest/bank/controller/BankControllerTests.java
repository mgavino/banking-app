package com.mgavino.bankingrest.bank.controller;

import com.mgavino.bankingrest.bank.service.AccountService;
import com.mgavino.bankingrest.bank.service.MovementService;
import com.mgavino.bankingrest.bank.service.dto.AccountDto;
import com.mgavino.bankingrest.bank.service.dto.AccountResultDto;
import com.mgavino.bankingrest.bank.service.dto.MovementDto;
import com.mgavino.bankingrest.bank.service.dto.MovementResultDto;
import com.mgavino.bankingrest.bank.service.enums.MovementType;
import com.mgavino.bankingrest.core.GenericControllerTests;
import com.mgavino.bankingrest.utils.UtilTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BankControllerTests extends GenericControllerTests {

	private static final String URI = "/bank";

	@Autowired
	private BankController bankAccountController;

	@MockBean
	private MovementService movementService;
	@MockBean
	private AccountService accountService;

	@Test
	public void contextLoads() {
		Assert.assertNotNull(bankAccountController);
	}

	@Test
	public void post() throws Exception {

		// bank save mock
		Mockito.when(accountService.insert(Mockito.any(AccountDto.class)))
				.thenReturn( UtilTests.createAccountResultDto(2L, 0.0) );

		// try create bank account
		AccountDto bank = new AccountDto();
		bank.setUser(1L);
		ResultActions result = post(URI, bank);

		// check 301
		AccountResultDto bankResponse = checkStatusReturnObj(result, HttpStatus.CREATED, AccountResultDto.class);

		// check location header
		result.andExpect(MockMvcResultMatchers.header().exists(HttpHeaders.LOCATION));

		// check response
		Assert.assertNotNull(bankResponse);
		Assert.assertEquals(Long.valueOf(2L), bankResponse.getId());

	}

	@Test
	public void getAll() throws Exception {

		// bank get mock
		List<AccountResultDto> mockResults = new ArrayList<>();
		mockResults.add( UtilTests.createAccountResultDto(1L, 0.0));
		mockResults.add( UtilTests.createAccountResultDto(2L, 0.0));
		Mockito.when( accountService.find(Mockito.any()) )
				.thenReturn( mockResults );

		// try get bank accounts by user
		Map<String, String> params = new HashMap<>();
		params.put("user", "1");
		ResultActions result = get(URI, params);

		// check 200
		List<AccountResultDto> banksResponse = checkStatusReturnList(result, HttpStatus.OK, AccountResultDto.class);

		// check response
		Assert.assertNotNull(banksResponse);
		Assert.assertEquals(2, banksResponse.size());

	}

	@Test
	public void get() throws Exception {

		// bank get mock
		AccountResultDto mockResult = UtilTests.createAccountResultDto(1L, 0.0);
		Mockito.when( accountService.get(Mockito.eq(1L)) ).thenReturn( mockResult );

		//try get bank
		ResultActions result = get(URI + "/1");

		// check 200
		AccountResultDto bankResponse = checkStatusReturnObj(result, HttpStatus.OK, AccountResultDto.class);

		// check response
		Assert.assertNotNull(bankResponse);
		Assert.assertEquals(Long.valueOf(1L), bankResponse.getId());

	}

	@Test
	public void movements() throws Exception {

		// movements mock
		List<MovementResultDto> mockResults = new ArrayList<>();
		mockResults.add(UtilTests.createMovementResultDto(1L, 20.0));
		mockResults.add(UtilTests.createMovementResultDto(2L, -10.0));
		Mockito.when( movementService.find(Mockito.eq(1L),  Mockito.any())).thenReturn(mockResults);

		// try get movements
		ResultActions result = get(URI + "/1/movements");

		// check 200
		List<MovementResultDto> movementResults = checkStatusReturnList(result, HttpStatus.OK, MovementResultDto.class);

		// check response
		Assert.assertNotNull(movementResults);
		Assert.assertEquals(2, movementResults.size());

	}

	@Test
	public void deposit() throws Exception {

		// deposit mock
		MovementResultDto mockResult = UtilTests.createMovementResultDto(1L, 20.0);
		Mockito.when( movementService.insert(Mockito.eq(1L), Mockito.eq(MovementType.DEPOSIT), Mockito.any()) )
					.thenReturn( mockResult );

		// try deposit
		MovementDto movementDto = new MovementDto();
		movementDto.setAmount(20.0);
		ResultActions result = post(URI + "/1/deposit", movementDto);

		// check 200
		MovementResultDto resultDto = checkStatusReturnObj(result, HttpStatus.OK, MovementResultDto.class);

		// check response
		Assert.assertNotNull(resultDto);
		Assert.assertEquals(Double.valueOf(20.0), resultDto.getAmount());

	}

	@Test
	public void withdraw() throws Exception {

		// deposit mock
		MovementResultDto mockResult = UtilTests.createMovementResultDto(1L, -20.0);
		Mockito.when( movementService.insert(Mockito.eq(1L), Mockito.eq(MovementType.WITHDRAW), Mockito.any()) )
				.thenReturn( mockResult );

		// try deposit
		MovementDto movementDto = new MovementDto();
		movementDto.setAmount(20.0);
		ResultActions result = post(URI + "/1/withdraw", movementDto);

		// check 200
		MovementResultDto resultDto = checkStatusReturnObj(result, HttpStatus.OK, MovementResultDto.class);

		// check response
		Assert.assertNotNull(resultDto);
		Assert.assertEquals(Double.valueOf(-20.0), resultDto.getAmount());

	}

}
