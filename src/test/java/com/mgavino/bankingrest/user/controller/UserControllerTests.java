package com.mgavino.bankingrest.user.controller;

import com.mgavino.bankingrest.core.GenericControllerTests;
import com.mgavino.bankingrest.user.service.UserService;
import com.mgavino.bankingrest.user.service.dto.UserDto;
import com.mgavino.bankingrest.user.service.dto.UserResultDto;
import com.mgavino.bankingrest.utils.UtilTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests extends GenericControllerTests {

	private static final String URI = "/user";

	@Autowired
	private UserController userController;

	@MockBean
	private UserService userService;

	@Test
	public void contextLoads() {
		Assert.assertNotNull(userController);
	}

	@Test
	public void post() throws Exception {

		// user save mock
		Mockito.when(userService.insert(Mockito.any(UserDto.class)))
				.thenReturn( UtilTests.createUserResultDto(1L) );

		// try create bank account
		UserDto user = new UserDto();
		user.setEmail("test1@mgavino.com");
		user.setPassword("password");
		ResultActions result = post(URI, user);

		// check 301
		UserResultDto userResponse = checkStatusReturnObj(result, HttpStatus.CREATED, UserResultDto.class);

		// check location header
		result.andExpect(MockMvcResultMatchers.header().exists(HttpHeaders.LOCATION));

		// check response
		Assert.assertNotNull(userResponse);
		Assert.assertEquals(Long.valueOf(1L), userResponse.getId());

	}

	@Test
	public void get() throws Exception {

		// user get mock
		Mockito.when(userService.get(Mockito.eq(1L)))
				.thenReturn(UtilTests.createUserResultDto(1L));

		// try get
		ResultActions result = get(URI + "/1");

		// check 200
		UserResultDto userResponse = checkStatusReturnObj(result, HttpStatus.OK, UserResultDto.class);

		// check response
		Assert.assertNotNull(userResponse);
		Assert.assertEquals(Long.valueOf(1L), userResponse.getId());

	}

}
