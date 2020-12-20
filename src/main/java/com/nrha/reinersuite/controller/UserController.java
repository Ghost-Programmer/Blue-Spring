package com.nrha.reinersuite.controller;

import com.nrha.reinersuite.dto.users.ChangePassword;
import com.nrha.reinersuite.dto.StatusMessage;
import com.nrha.reinersuite.dto.users.UserSearch;
import com.nrha.reinersuite.models.users.User;
import com.nrha.reinersuite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/change-password")
    public StatusMessage changePasword(@RequestBody ChangePassword changePassword) {
        return userService.changePassword(changePassword);
    }

    @PostMapping("user-search")
    public UserSearch userSearch(@RequestBody UserSearch userSearch) {
        return userService.search(userSearch);
    }

    @PutMapping("")
    public User userSave(@RequestBody User user) throws IllegalAccessException {
        return this.userService.userSave(user);
    }

    @PostMapping("")
    public User userCreate(@RequestBody User user) throws IllegalAccessException {
        return this.userService.userCreate(user);
    }

    @DeleteMapping("{id}")
    public User userSave(@PathVariable("id") Long userId) throws IllegalAccessException {
        return this.userService.userDelete(userId);
    }
}
