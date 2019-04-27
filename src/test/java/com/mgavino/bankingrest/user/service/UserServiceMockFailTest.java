package com.mgavino.bankingrest.user.service;

import com.mgavino.bankingrest.core.exception.AlreadyExistsException;
import com.mgavino.bankingrest.core.exception.NotFoundException;
import com.mgavino.bankingrest.user.repository.UserRepository;
import com.mgavino.bankingrest.user.service.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceMockFailTest {

	@Autowired
	private UserService service;

	@MockBean
	private UserRepository repository;

	@Test(expected = AlreadyExistsException.class)
	public void insertAlreadyExists() throws Exception {

		// mock
		Mockito.when(repository.countByEmail("test21@mgavino.com")).thenReturn(1);

		// call
		UserDto userDto = new UserDto();
		userDto.setEmail("test21@mgavino.com");
		userDto.setPassword("password");
		service.insert(userDto);

	}

	@Test(expected = NotFoundException.class)
	public void getNotFound() throws Exception {

		// mock
		Mockito.when(repository.findById(1L)).thenReturn(Optional.empty());

		// call
		service.get(1L);

	}

}
