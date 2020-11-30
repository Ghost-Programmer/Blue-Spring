package com.nrha.reinersuite.controller;

import com.nrha.reinersuite.dto.ChangePassword;
import com.nrha.reinersuite.dto.StatusMessage;
import com.nrha.reinersuite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/change-password")
    public StatusMessage changePasword(@RequestBody ChangePassword changePassword) {
        return userService.changePassword(changePassword);
    }
}
