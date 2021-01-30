package com.blue.project.controller;

import com.blue.project.dto.users.ChangePassword;
import com.blue.project.dto.StatusMessage;
import com.blue.project.dto.users.ChangeUserPassword;
import com.blue.project.dto.users.UserRole;
import com.blue.project.dto.users.UserSearch;
import com.blue.project.models.users.SecurityRole;
import com.blue.project.models.users.User;
import com.blue.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @RolesAllowed("ROLE_CHANGE_OWN_PASSWORD")
    @PostMapping("/change-password")
    public StatusMessage changePasword(@RequestBody ChangePassword changePassword) {
        return userService.changePassword(changePassword);
    }

    @RolesAllowed("ROLE_CHANGE_USER_PASSWORD")
    @PostMapping("/change-user-password")
    public StatusMessage changeUserPasword(@RequestBody ChangeUserPassword changeUserPassword) {
        return userService.changeUserPassword(changeUserPassword);
    }

    @PostMapping("user-search")
    public UserSearch userSearch(@RequestBody UserSearch userSearch) {
        return userService.search(userSearch);
    }

    @PutMapping("")
    public User userSave(@RequestBody User user) throws IllegalAccessException {
        return this.userService.userSave(user);
    }

    @RolesAllowed("ROLE_USER_MANAGEMENT")
    @DeleteMapping("{id}")
    public User userDelete(@PathVariable("id") Long userId) throws IllegalAccessException {
        return this.userService.userDelete(userId);
    }

    @RolesAllowed("ROLE_USER_MANAGEMENT")
    @GetMapping("{id}/roles")
    public List<UserRole> getUserRoles(@PathVariable("id") Long userId) {
        return this.userService.getUserRoles(userId);
    }

    @RolesAllowed("ROLE_USER_MANAGEMENT")
    @PutMapping("{id}/roles")
    public List<UserRole> putUserRoles(@PathVariable("id") long userId, @RequestBody List<UserRole> roles) {
        return this.userService.putUserRoles(userId,roles);
    }
}
