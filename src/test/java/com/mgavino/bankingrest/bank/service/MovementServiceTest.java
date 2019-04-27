package com.mgavino.bankingrest.bank.service;

import com.mgavino.bankingrest.bank.repository.AccountRepository;
import com.mgavino.bankingrest.bank.repository.MovementRepository;
import com.mgavino.bankingrest.bank.repository.model.AccountEntity;
import com.mgavino.bankingrest.bank.repository.model.MovementEntity;
import com.mgavino.bankingrest.bank.service.dto.MovementDto;
import com.mgavino.bankingrest.bank.service.dto.MovementFilterDto;
import com.mgavino.bankingrest.bank.service.dto.MovementResultDto;
import com.mgavino.bankingrest.bank.service.enums.MovementType;
import com.mgavino.bankingrest.user.repository.UserRepository;
import com.mgavino.bankingrest.user.repository.model.UserEntity;
import com.mgavino.bankingrest.utils.UtilTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovementServiceTest {

	@Autowired
	private MovementService service;

	@Autowired
	private MovementRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Test
	public void contextLoads() {
		Assert.assertNotNull(service);
	}

	@Test
	public void insert() throws Exception {

		// insert data
		UserEntity user = userRepository.save(UtilTests.createUserEntity("test16@mgavino.com", "password"));
		AccountEntity account = accountRepository.save(UtilTests.createAccountEntity(user.getId(), "Account 16", 2.0));

		// call
		MovementDto movement = new MovementDto();
		movement.setAmount(3.00);
		MovementResultDto depositDto = service.insert(account.getId(), MovementType.DEPOSIT, movement);
		movement.setAmount(4.00);
		MovementResultDto withdrawDto = service.insert(account.getId(), MovementType.WITHDRAW, movement);

		Optional<AccountEntity> savedAccountOpt = accountRepository.findById(account.getId());

		// check
		Assert.assertNotNull(depositDto);
		Assert.assertEquals(Double.valueOf(3.00), depositDto.getAmount());
		Assert.assertNotNull(withdrawDto);
		Assert.assertEquals(Double.valueOf(-4.00), withdrawDto.getAmount());
		Assert.assertTrue(savedAccountOpt.isPresent());
		Assert.assertEquals(Double.valueOf(1.00), savedAccountOpt.get().getBalance());

	}

	@Test
	public void find() throws Exception {

		// insert data
		UserEntity user = userRepository.save(UtilTests.createUserEntity("test17@mgavino.com", "password"));
		AccountEntity account = accountRepository.save(UtilTests.createAccountEntity(user.getId(), "Account 17", 10.0));

		Date date1 = new Date();
		Thread.sleep(1000);
		MovementEntity movement1 = repository.save(UtilTests.createMovementEntity(account.getId(), "Concept", 10.00));
		MovementEntity movement2 = repository.save(UtilTests.createMovementEntity(account.getId(), "Concept", 10.00));
		Thread.sleep(1000);
		Date date2 = new Date();
		Thread.sleep(1000);
		MovementEntity movement3 = repository.save(UtilTests.createMovementEntity(account.getId(), "Concept", 10.00));
		Thread.sleep(1000);
		Date date3 = new Date();

		// call
		MovementFilterDto filter = new MovementFilterDto();
		filter.setFrom(date2);
		filter.setTo(date3);
		List<MovementResultDto> movements = service.find(account.getId(), filter);

		// check
		Assert.assertNotNull(movements);
		Assert.assertEquals(1, movements.size());
		Assert.assertEquals(movement3.getId(), movements.get(0).getId());

		// call
		filter.setFrom(date1);
		filter.setTo(date2);
		movements = service.find(account.getId(), filter);

		// check
		Assert.assertNotNull(movements);
		Assert.assertEquals(2, movements.size());

	}

}
