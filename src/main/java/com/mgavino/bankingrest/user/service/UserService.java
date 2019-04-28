package com.mgavino.bankingrest.user.service;

import com.mgavino.bankingrest.user.service.dto.UserDto;
import com.mgavino.bankingrest.user.service.dto.UserResultDto;

public interface UserService {

    /**
     * Create new User
     * @param userDto
     * @return
     * @throws Exception
     */
    public UserResultDto insert(UserDto userDto) throws Exception;

    /**
     * Find User by ID
     * @param id
     * @return
     * @throws Exception
     */
    public UserResultDto get(Long id) throws Exception;

}
