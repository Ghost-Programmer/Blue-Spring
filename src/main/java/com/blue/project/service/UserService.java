package com.blue.project.service;

import com.blue.project.dto.users.*;
import com.blue.project.dto.StatusMessage;
import com.blue.project.models.users.SecurityRole;
import com.blue.project.models.users.User;

import java.util.List;

public interface UserService {
    StatusMessage registerUser(Registration registration);

    StatusMessage userVerification(String token);
    User getUserByUsername(String username);
    User getCurrentUser();
    String getCurrentUserName();
    User userSave(User user) throws IllegalAccessException;
    User userCreate(User user) throws IllegalAccessException;
    User userDelete(Long userId) throws IllegalAccessException;
    List<SecurityRole> getRolesByUser(User user);
    boolean hasAuthority(List<SecurityRole> roles, String role);

    StatusMessage changePassword(ChangePassword changePassword);
    StatusMessage changeUserPassword(ChangeUserPassword changeUserPassword);

    UserSearch search(UserSearch userSearch);
    List<UserRole> getUserRoles(Long userId);

    List<UserRole> putUserRoles(long userId, List<UserRole> roles);
}
