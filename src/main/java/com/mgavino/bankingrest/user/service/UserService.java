package com.mgavino.bankingrest.user.service;

import com.mgavino.bankingrest.user.service.dto.UserDto;
import com.mgavino.bankingrest.user.service.dto.UserResultDto;

public interface UserService {

    public UserResultDto insert(UserDto userDto) throws Exception;

    public UserResultDto get(Long id) throws Exception;

}
