package com.mgavino.bankingrest.user.service;

import com.mgavino.bankingrest.user.repository.UserRepository;
import com.mgavino.bankingrest.user.repository.model.UserEntity;
import com.mgavino.bankingrest.user.service.dto.UserDto;
import com.mgavino.bankingrest.user.service.dto.UserResultDto;
import com.mgavino.bankingrest.utils.UtilTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService service;

	@Autowired
	private UserRepository repository;

	@Test
	public void contextLoads() {
		Assert.assertNotNull(service);
	}

	@Test
	public void insert() throws Exception {

		// call
		UserDto userDto = new UserDto();
		userDto.setEmail("test22@mgavino.com");
		userDto.setPassword("password");
		UserResultDto resultDto = service.insert(userDto);

		// check
		Assert.assertNotNull(resultDto);

	}

	@Test
	public void get() throws Exception {

		// insert data
		UserEntity user = repository.save(UtilTests.createUserEntity("test23@mgavino.com", "password"));

		// call
		UserResultDto resultDto = service.get(user.getId());

		// check
		Assert.assertNotNull(resultDto);
		Assert.assertEquals(user.getId(), resultDto.getId());


	}

}
