package com.blue.project.modules.users.services;

import com.blue.project.dto.StatusMessage;
import com.blue.project.modules.users.dto.*;
import com.blue.project.modules.users.models.SecurityRole;
import com.blue.project.modules.users.models.User;

import java.util.Collection;
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

    boolean hasAuthority(Collection<SecurityRole> roles, String role);

    boolean hasAuthority(Collection<SecurityRole> roles, Collection<SecurityRole> checkRoles);

    boolean currentUserHasAuthority(Collection<String> roles);

    StatusMessage changePassword(ChangePassword changePassword);

    StatusMessage changeUserPassword(ChangeUserPassword changeUserPassword);

    UserSearch search(UserSearch userSearch);

    List<UserRole> getUserRoles(Long userId);

    boolean hasAuthority(User user, String role);

    boolean hasAuthority(User user, Collection<String> roles);

    List<UserRole> putUserRoles(long userId, List<UserRole> roles);

    List<SecurityRole> getSecurityRoles();
}
