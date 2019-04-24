package com.mgavino.bankingrest.user.controller;

import com.mgavino.bankingrest.user.model.UserEntity;
import com.mgavino.bankingrest.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping( method = {RequestMethod.POST} )
    public ResponseEntity<UserEntity> signup(@RequestParam String email, @RequestParam String password) {
        // TODO
        return null;
    }

}
