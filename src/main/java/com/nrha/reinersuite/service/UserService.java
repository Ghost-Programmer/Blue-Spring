package com.nrha.reinersuite.service;

import com.nrha.reinersuite.dto.users.ChangePassword;
import com.nrha.reinersuite.dto.users.Registration;
import com.nrha.reinersuite.dto.StatusMessage;
import com.nrha.reinersuite.dto.users.UserRole;
import com.nrha.reinersuite.dto.users.UserSearch;
import com.nrha.reinersuite.models.users.SecurityRole;
import com.nrha.reinersuite.models.users.User;

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

    UserSearch search(UserSearch userSearch);
    List<UserRole> getUserRoles(Long userId);

    List<UserRole> putUserRoles(long userId, List<UserRole> roles);
}
