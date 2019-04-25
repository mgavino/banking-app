package com.mgavino.bankingrest.user.service.impl;

import com.mgavino.bankingrest.bank.repository.model.BankAccountEntity;
import com.mgavino.bankingrest.bank.service.BankAccountService;
import com.mgavino.bankingrest.core.exception.AlreadyExistsException;
import com.mgavino.bankingrest.user.repository.model.UserEntity;
import com.mgavino.bankingrest.user.repository.UserRepository;
import com.mgavino.bankingrest.user.service.UserService;
import com.mgavino.bankingrest.user.service.dto.UserDto;
import com.mgavino.bankingrest.user.service.dto.UserResultDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Transactional
    public UserResultDto insert(UserDto userDto) throws Exception {

        // check already exists
        int count = repository.countByEmail(userDto.getEmail());
        if (count == 0) {

            // insert new user
            UserEntity user = mapper.map(userDto, UserEntity.class);
            // TODO: implement PasswordEncoder
            UserEntity savedUser = repository.save(user);

            // TODO: create first account by default?

            // parse to result DTO
            UserResultDto resultDto = mapper.map(savedUser, UserResultDto.class);
            return resultDto;

        } else {
            throw new AlreadyExistsException();
        }

    }
}
