package com.blue.project.security;

import com.blue.project.models.users.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JdbcUserDetails implements UserDetails {
    private final User user;

    public JdbcUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        if(user == null) {
            return true;
        }
        return !user.getAccountExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        if(user == null) {
            return true;
        }
        return !user.getAccountLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if(user == null) {
            return true;
        }
        return user.getEnabled();
    }

    @Override
    public boolean isEnabled() {
        if(user == null) {
            return true;
        }
        return user.getEnabled();
    }
}
