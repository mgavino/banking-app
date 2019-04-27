package com.mgavino.bankingrest.bank.service;

import com.mgavino.bankingrest.bank.repository.AccountRepository;
import com.mgavino.bankingrest.bank.repository.model.AccountEntity;
import com.mgavino.bankingrest.bank.service.dto.AccountDto;
import com.mgavino.bankingrest.bank.service.dto.AccountFilterDto;
import com.mgavino.bankingrest.bank.service.dto.AccountResultDto;
import com.mgavino.bankingrest.user.repository.UserRepository;
import com.mgavino.bankingrest.user.repository.model.UserEntity;
import com.mgavino.bankingrest.utils.UtilTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Test
	public void contextLoads() {
		Assert.assertNotNull(accountService);
	}

	@Test
	public void insert() throws Exception {

		//insert data
		UserEntity user = userRepository.save(UtilTests.createUserEntity("test@mgavino.com", "password"));

		// call
		AccountDto account = new AccountDto();
		account.setUser(user.getId());
		account.setName("Account");
		AccountResultDto resultDto = accountService.insert(account);

		// check
		Assert.assertNotNull(resultDto);
		Assert.assertEquals(user.getId(), resultDto.getId());
		Assert.assertEquals("Account", resultDto.getName());
		Assert.assertEquals(Double.valueOf(0.0), resultDto.getBalance());

	}

	@Test
	public void find() throws Exception {

		// insert data
		UserEntity user = userRepository.save(UtilTests.createUserEntity("test1@mgavino.com", "password"));
		UserEntity user2 = userRepository.save(UtilTests.createUserEntity("test2@mgavino.com", "password"));
		accountRepository.save(UtilTests.createAccountEntity(user.getId(), "Account 1", 10.0));
		accountRepository.save(UtilTests.createAccountEntity(user.getId(), "Account 2", 20.0));
		accountRepository.save(UtilTests.createAccountEntity(user2.getId(), "Account 3", 30.0));

		// call user 1
		AccountFilterDto filter = new AccountFilterDto();
		filter.setUser(user.getId());
		List<AccountResultDto> resultDtos = accountService.find(filter);
		// call user 2
		filter.setUser(user2.getId());
		List<AccountResultDto> result2Dtos = accountService.find(filter);

		// check user 1
		Assert.assertNotNull(resultDtos);
		Assert.assertEquals(2, resultDtos.size());
		// check user 2
		Assert.assertNotNull(result2Dtos);
		Assert.assertEquals(1, result2Dtos.size());

	}

	@Test
	public void get() throws Exception {

		// insert data
		UserEntity user = userRepository.save(UtilTests.createUserEntity("test4@mgavino.com", "password"));
		AccountEntity account = accountRepository.save(UtilTests.createAccountEntity(user.getId(), "Account 3", 15.0));

		// call
		AccountResultDto resultDto = accountService.get(account.getId());

		// check
		Assert.assertNotNull(resultDto);
		Assert.assertEquals(account.getId(), resultDto.getId());
		Assert.assertEquals("Account 3", resultDto.getName());
		Assert.assertEquals(Double.valueOf(15.0), resultDto.getBalance());

	}

	@Test
	public void refreshBalance() throws Exception {

		// insert data
		UserEntity user = userRepository.save(UtilTests.createUserEntity("test8@mgavino.com", "password"));
		AccountEntity account = accountRepository.save(UtilTests.createAccountEntity(user.getId(), "Account 8", 15.0));

		// call
		accountService.refreshBalance(account.getId(), -6.00);
		AccountResultDto resultDto = accountService.refreshBalance(account.getId(), 2.00);

		// check
		Assert.assertNotNull(resultDto);
		Assert.assertEquals(account.getId(), resultDto.getId());
		Assert.assertEquals(Double.valueOf(11.00), resultDto.getBalance());

	}

}
