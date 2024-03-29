package com.blue.project.security;

import com.blue.project.modules.users.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public record JdbcUserDetails(User user) implements UserDetails {

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
        if (user == null) {
            return true;
        }
        return !user.getAccountExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        if (user == null) {
            return true;
        }
        return !user.getAccountLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if (user == null) {
            return true;
        }
        return user.getEnabled();
    }

    @Override
    public boolean isEnabled() {
        if (user == null) {
            return true;
        }
        return user.getEnabled();
    }
}
