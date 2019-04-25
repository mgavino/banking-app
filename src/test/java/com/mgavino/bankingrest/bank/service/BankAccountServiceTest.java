package com.mgavino.bankingrest.bank.service;

import com.mgavino.bankingrest.user.controller.UserController;
import com.mgavino.bankingrest.user.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankAccountServiceTest {

	@Autowired
	private BankAccountService bankAccountService;

	@Test
	public void contextLoads() {
		Assert.assertNotNull(bankAccountService);
	}

}
