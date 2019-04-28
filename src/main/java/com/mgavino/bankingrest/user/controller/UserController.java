package com.mgavino.bankingrest.user.controller;

import com.mgavino.bankingrest.user.service.UserService;
import com.mgavino.bankingrest.user.service.dto.UserDto;
import com.mgavino.bankingrest.user.service.dto.UserResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<UserResultDto> post(@RequestBody @Valid UserDto userDto, UriComponentsBuilder ucBuilder) throws Exception {
        // insert user
        UserResultDto result = userService.insert(userDto);

        // build response
        URI location = ucBuilder.path("/user/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).body(result);
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.GET)
    public UserResultDto get(@PathVariable("id") Long id) throws Exception {
        // get user
        return userService.get(id);
    }

}
