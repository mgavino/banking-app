package com.mgavino.bankingrest.bank.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgavino.bankingrest.bank.controller.BankAccountController;
import com.mgavino.bankingrest.bank.repository.BankAccountRepository;
import com.mgavino.bankingrest.bank.repository.MovementRepository;
import com.mgavino.bankingrest.bank.repository.model.BankAccountEntity;
import com.mgavino.bankingrest.bank.service.BankAccountService;
import com.mgavino.bankingrest.bank.service.MovementService;
import com.mgavino.bankingrest.bank.service.dto.BankAccountDto;
import com.mgavino.bankingrest.bank.service.dto.BankAccountFilterDto;
import com.mgavino.bankingrest.bank.service.dto.BankAccountResultDto;
import com.mgavino.bankingrest.user.repository.UserRepository;
import com.mgavino.bankingrest.user.repository.model.UserEntity;
import com.mgavino.bankingrest.utils.TestUtils;
import org.apache.catalina.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BankAccountControllerTests {

	@Autowired
	private BankAccountController bankAccountController;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private MovementService movementService;
	@MockBean
	private BankAccountService bankAccountService;

	@Test
	public void contextLoads() {
		Assert.assertNotNull(bankAccountController);
	}

	@Test
	public void post() throws Exception {

		// bank save mock
		Mockito.when(bankAccountService.insert(Mockito.any(BankAccountDto.class)))
				.thenReturn( TestUtils.createBankResultDto(2L, 0.0) );

		// try create bank account
		BankAccountDto bank = new BankAccountDto();
		bank.setUserId(1L);
		ResultActions result = mockMvc.perform(
				MockMvcRequestBuilders.post( "/bank-account" )
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(bank)));

		// check 301
		String contentResponse = result
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andReturn().getResponse().getContentAsString();

		// check response
		BankAccountResultDto bankResponse = objectMapper.reader().forType(BankAccountResultDto.class)
				.readValue(contentResponse);
		Assert.assertNotNull(bankResponse);
		Assert.assertEquals(Long.valueOf(2L), bankResponse.getId());

	}

	@Test
	public void getAll() throws Exception {

		// mock find user
		List<BankAccountResultDto> results = new ArrayList<>();
		results.add( TestUtils.createBankResultDto(10L, 0.0));
		results.add( TestUtils.createBankResultDto(11L, 0.0));

		Mockito.when( bankAccountService.find(Mockito.any(BankAccountFilterDto.class)) )
				.thenReturn( results );

		// try get bank accounts for user 1
		ResultActions result = mockMvc.perform(
				MockMvcRequestBuilders.get( "/bank-account" )
						.contentType(MediaType.APPLICATION_JSON)
						.param("userId", "1"));

		// check 200
		String contentResponse = result
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getResponse().getContentAsString();

		// check response
		List<BankAccountResultDto> banksResponse = objectMapper
				.readValue(contentResponse, new TypeReference<List<BankAccountResultDto>>(){});
		Assert.assertNotNull(banksResponse);
		Assert.assertEquals(2, banksResponse.size());

	}

	@Test
	public void get() throws Exception {

	}

	@Test
	public void movements() throws Exception {

	}

	@Test
	public void deposit() throws Exception {

	}

	@Test
	public void depositNotFound() throws Exception {

	}

	@Test
	public void withdraw() throws Exception {

	}

}
