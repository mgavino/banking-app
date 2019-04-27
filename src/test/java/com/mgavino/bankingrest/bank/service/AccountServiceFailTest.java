package com.mgavino.bankingrest.bank.service;

import com.mgavino.bankingrest.bank.repository.AccountRepository;
import com.mgavino.bankingrest.bank.repository.model.AccountEntity;
import com.mgavino.bankingrest.core.exception.NotEnoughMoneyException;
import com.mgavino.bankingrest.user.repository.UserRepository;
import com.mgavino.bankingrest.user.repository.model.UserEntity;
import com.mgavino.bankingrest.utils.UtilTests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceFailTest {

	@Autowired
	private AccountService accountService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Test(expected = NotEnoughMoneyException.class)
	public void refreshBalanceNotEnoughMoney() throws Exception {

		// insert data
		UserEntity user = userRepository.save(UtilTests.createUserEntity("test10@mgavino.com", "password"));
		AccountEntity account = accountRepository.save(UtilTests.createAccountEntity(user.getId(), "Account 10", 5.0));

		// check
		accountService.refreshBalance(account.getId(), -6.0);

	}

}
