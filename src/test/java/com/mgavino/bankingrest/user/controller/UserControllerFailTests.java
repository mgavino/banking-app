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
	public void postNotFound() throws Exception {

		// mock service
		Mockito.when(userService.insert(Mockito.any()))
				.thenThrow(new NotFoundException());

		// try create user
		UserDto user = new UserDto();
		user.setEmail("test1@mgavino.com");
		user.setPassword("password");
		ResultActions result = post(URI, user);

		// check 404 (not found)
		checkStatus(result, HttpStatus.NOT_FOUND);

	}

	@Test
	public void postBadRequest() throws Exception {

		// try create user
		UserDto user = new UserDto();
		user.setEmail("test1@mgavino.com");
		ResultActions result = post(URI, user);

		// check 400 (invalid parameters)
		checkStatus(result, HttpStatus.BAD_REQUEST);

	}

	@Test
	public void getNotFound() throws Exception {

		// mock service
		Mockito.when(userService.get(Mockito.eq(1L)))
				.thenThrow(new NotFoundException());

		// try get user
		ResultActions result = get(URI + "/1");

		// check 404 (not found)
		checkStatus(result, HttpStatus.NOT_FOUND);

	}
}
