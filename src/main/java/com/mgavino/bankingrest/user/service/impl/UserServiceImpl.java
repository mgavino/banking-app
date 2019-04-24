package com.mgavino.bankingrest.user.service.impl;

import com.mgavino.bankingrest.user.model.UserEntity;
import com.mgavino.bankingrest.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Override
    @Transactional(readOnly = false)
    public UserEntity save(UserService entity) {
        //TODO: save new user
        //TODO: create first account by default
        return null;
    }
}
