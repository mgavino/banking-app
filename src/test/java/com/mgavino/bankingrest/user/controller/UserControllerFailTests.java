package com.mgavino.bankingrest.user.controller;

import com.mgavino.bankingrest.core.GenericControllerTests;
import com.mgavino.bankingrest.core.exception.NotFoundException;
import com.mgavino.bankingrest.user.service.UserService;
import com.mgavino.bankingrest.user.service.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerFailTests extends GenericControllerTests {

	private static final String URI = "/user";

	@MockBean
	private UserService userService;

	@Test
	public void depositNotFound() throws Exception {

		// mock service
		Mockito.when(userService.insert(Mockito.any()))
				.thenThrow(new NotFoundException());

		// try create withdraw
		UserDto user = new UserDto();
		user.setEmail("test1@mgavino.com");
		user.setPassword("password");
		ResultActions result = post(URI, user);

		// check 404 (not found)
		checkStatus(result, HttpStatus.NOT_FOUND);

	}

	@Test
	public void depositBadRequest() throws Exception {

		// try create deposit
		UserDto user = new UserDto();
		user.setEmail("test1@mgavino.com");
		ResultActions result = post(URI, user);

		// check 400 (invalid parameters)
		checkStatus(result, HttpStatus.BAD_REQUEST);

	}
}
