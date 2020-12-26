package com.nrha.reinersuite.dto.users;

import com.nrha.reinersuite.models.users.SecurityRole;

import javax.persistence.Column;

public class UserRole {

    private Long userId;
    private Long securityRoleId;
    private Boolean active;
    private String name;
    private String category;
    private String description;

    public UserRole(Long userId, SecurityRole role) {
        this.userId = userId;
        this.name = role.getName();
        this.category = role.getCategory();
        this.description = role.getDescription();
        this.securityRoleId = role.getId();
        this.active = false;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSecurityRoleId() {
        return securityRoleId;
    }

    public void setSecurityRoleId(Long securityRoleId) {
        this.securityRoleId = securityRoleId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
