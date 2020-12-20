package com.nrha.reinersuite.service;

import com.nrha.reinersuite.dto.users.ChangePassword;
import com.nrha.reinersuite.dto.users.Registration;
import com.nrha.reinersuite.dto.StatusMessage;
import com.nrha.reinersuite.dto.users.UserSearch;
import com.nrha.reinersuite.models.users.SecurityRole;
import com.nrha.reinersuite.models.users.User;
import name.mymiller.nadia.Nadia;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

public interface UserService {
    StatusMessage registerUser(Registration registration) throws Exception;

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

    public UserSearch search(UserSearch userSearch);
}
