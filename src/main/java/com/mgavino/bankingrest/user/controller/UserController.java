package com.mgavino.bankingrest.user.controller;

import com.mgavino.bankingrest.user.service.UserService;
import com.mgavino.bankingrest.user.service.dto.UserDto;
import com.mgavino.bankingrest.user.service.dto.UserResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method=RequestMethod.POST)
    public UserResultDto signup(@RequestBody @Valid UserDto userDto) throws Exception {
        return userService.insert(userDto);
    }

}
