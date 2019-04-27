package com.mgavino.bankingrest.bank.service;

import com.mgavino.bankingrest.bank.repository.AccountRepository;
import com.mgavino.bankingrest.bank.repository.model.AccountEntity;
import com.mgavino.bankingrest.bank.service.dto.MovementDto;
import com.mgavino.bankingrest.bank.service.enums.MovementType;
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
public class MovementServiceFailTest {

	@Autowired
	private MovementService movementService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Test(expected = NotEnoughMoneyException.class)
	public void insertNotEnoughMoney() throws Exception {

		// insert data
		UserEntity user = userRepository.save(UtilTests.createUserEntity("test15@mgavino.com", "password"));
		AccountEntity account = accountRepository.save(UtilTests.createAccountEntity(user.getId(), "Account 15", 2.0));

		// check
		MovementDto movement = new MovementDto();
		movement.setAmount(4.00);
		movementService.insert(account.getUser(), MovementType.WITHDRAW, movement);

	}

}
