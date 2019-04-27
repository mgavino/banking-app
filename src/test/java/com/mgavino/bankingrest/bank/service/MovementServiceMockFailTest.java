package com.mgavino.bankingrest.bank.service;

import com.mgavino.bankingrest.bank.repository.AccountRepository;
import com.mgavino.bankingrest.bank.repository.MovementRepository;
import com.mgavino.bankingrest.bank.service.dto.MovementDto;
import com.mgavino.bankingrest.bank.service.dto.MovementFilterDto;
import com.mgavino.bankingrest.bank.service.enums.MovementType;
import com.mgavino.bankingrest.core.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovementServiceMockFailTest {

	@Autowired
	private MovementService service;

	@MockBean
	private AccountRepository accountRepository;

	@MockBean
	private MovementRepository movementRepository;

	@Test(expected = NotFoundException.class)
	public void insertNotFound() throws Exception {

		// mock
		Mockito.when(accountRepository.findById(Mockito.eq(1L))).thenReturn(Optional.empty());

		// check
		MovementDto movement = new MovementDto();
		movement.setAmount(14.00);
		service.insert(1L, MovementType.DEPOSIT, movement);

	}

	@Test(expected = NotFoundException.class)
	public void findNotFound() throws Exception {

		// mock
		Mockito.when(movementRepository.findByBankAccountIdAndDateBetween(Mockito.eq(1L),
				Mockito.any(Date.class), Mockito.any(Date.class))).thenThrow(new NotFoundException());

		// check
		MovementFilterDto filter = new MovementFilterDto();
		service.find(1L, filter);

	}

}
