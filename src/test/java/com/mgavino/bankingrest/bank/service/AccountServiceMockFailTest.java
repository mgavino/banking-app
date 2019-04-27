package com.mgavino.bankingrest.bank.service;

import com.mgavino.bankingrest.bank.repository.AccountRepository;
import com.mgavino.bankingrest.bank.service.dto.AccountDto;
import com.mgavino.bankingrest.bank.service.dto.AccountFilterDto;
import com.mgavino.bankingrest.core.exception.NotFoundException;
import com.mgavino.bankingrest.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceMockFailTest {

	@Autowired
	private AccountService accountService;

	@MockBean
	private UserService userServiceMock;

	@MockBean
	private AccountRepository accountRepositoryMock;

	@Test(expected = NotFoundException.class)
	public void insertNotFound() throws Exception {

		// mock user
		Mockito.when(userServiceMock.get(Mockito.eq(1L))).thenThrow(new NotFoundException());

		// check
		AccountDto account = new AccountDto();
		account.setUser(1L);
		accountService.insert(account);

	}

	@Test(expected = NotFoundException.class)
	public void findNotFound() throws Exception {

		// mock
		Mockito.when(accountRepositoryMock.findAll(Mockito.any(Example.class))).thenReturn(new ArrayList());

		// check
		AccountFilterDto filter = new AccountFilterDto();
		filter.setUser(1L);
		accountService.find(filter);

	}

	@Test(expected = NotFoundException.class)
	public void getNotFound() throws Exception {

		// mock
		Mockito.when(accountRepositoryMock.findById(Mockito.eq(1L))).thenReturn(Optional.empty());

		// check
		accountService.get(1L);

	}

}
