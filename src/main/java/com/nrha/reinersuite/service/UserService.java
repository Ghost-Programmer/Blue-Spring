package com.nrha.reinersuite.service;

import com.nrha.reinersuite.dto.ChangePassword;
import com.nrha.reinersuite.dto.Registration;
import com.nrha.reinersuite.dto.StatusMessage;
import com.nrha.reinersuite.dto.UserSearch;
import com.nrha.reinersuite.models.users.SecurityRole;
import com.nrha.reinersuite.models.users.User;

import java.util.List;

public interface UserService {
    StatusMessage registerUser(Registration registration) throws Exception;

    StatusMessage userVerification(String token);
    User getUserByUsername(String username);
    List<SecurityRole> getRolesByUser(User user);
    boolean hasAuthority(List<SecurityRole> roles, String role);

    StatusMessage changePassword(ChangePassword changePassword);

    public UserSearch search(UserSearch userSearch);
}
