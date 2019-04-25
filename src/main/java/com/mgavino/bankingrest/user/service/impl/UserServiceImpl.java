package com.mgavino.bankingrest.user.service.impl;

import com.mgavino.bankingrest.bank.repository.model.BankAccountEntity;
import com.mgavino.bankingrest.bank.service.BankAccountService;
import com.mgavino.bankingrest.core.exception.AlreadyExistsException;
import com.mgavino.bankingrest.core.exception.NotFoundException;
import com.mgavino.bankingrest.user.repository.model.UserEntity;
import com.mgavino.bankingrest.user.repository.UserRepository;
import com.mgavino.bankingrest.user.service.UserService;
import com.mgavino.bankingrest.user.service.dto.UserDto;
import com.mgavino.bankingrest.user.service.dto.UserResultDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
            // TODO: implements PasswordEncoder with Spring Security
            UserEntity savedUser = repository.save(user);

            // parse to result DTO
            UserResultDto resultDto = mapper.map(savedUser, UserResultDto.class);
            return resultDto;

        } else {
            throw new AlreadyExistsException();
        }

    }

    public UserResultDto get(Long id) throws Exception {

        Optional<UserEntity> userEntityOpt = repository.findById(id);
        if (userEntityOpt.isPresent()) {
            // mapper
            UserResultDto resultDto = mapper.map(userEntityOpt.get(), UserResultDto.class);
            return resultDto;
        } else {
            // throw not found exception if it is not present
            throw new NotFoundException();
        }
    }
}
