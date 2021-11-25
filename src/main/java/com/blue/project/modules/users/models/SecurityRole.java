package com.blue.project.modules.users.models;

import com.blue.project.models.AbstractTimestampEntity;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "security_role", schema = "user", catalog = "user")
public class SecurityRole extends AbstractTimestampEntity implements Serializable {

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_USER_MANAGEMENT = "ROLE_USER_MANAGEMENT";
    public static final String ROLE_CHANGE_USER_PASSWORD = "ROLE_CHANGE_USER_PASSWORD";
    public static final String ROLE_CHANGE_OWN_PASSWORD = "ROLE_CHANGE_OWN_PASSWORD";
    public static final String ROLE_DEVELOPER = "ROLE_DEVELOPER";
    public static final String ROLE_MONITOR = "ROLE_MONITOR";
    public static final String ROLE_APPROVER = "ROLE_APPROVER";
    public static final String ROLE_DOCUMENT_MANAGER = "ROLE_DOCUMENT_MANAGER";
    public static final String ROLE_ADMIN_MAINTENANCE = "ROLE_ADMIN_MAINTENANCE";
    public static final String ROLE_ADMIN_QUARTZ = "ROLE_ADMIN_QUARTZ";
    public static final String ROLE_ADMIN_DBA = "ROLE_ADMIN_DBA";
    public static final String ROLE_DB_ACCESS = "ROLE_DB_ACCESS";
    public static final String ROLE_PAGE_EDITOR = "ROLE_PAGE_EDITOR";
    public static final String ROLE_MENU_EDITOR = "ROLE_MENU_EDITOR";


    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "authority", nullable = false)
    private String authority;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "default")
    private Boolean defaultRole;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getDefaultRole() {
        return defaultRole;
    }

    public void setDefaultRole(Boolean defaultRole) {
        this.defaultRole = defaultRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecurityRole that = (SecurityRole) o;
        return Objects.equals(id, that.id) && Objects.equals(authority, that.authority) && Objects.equals(name, that.name) && Objects.equals(category, that.category) && Objects.equals(description, that.description) && Objects.equals(defaultRole, that.defaultRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authority, name, category, description, defaultRole);
    }

    @Override
    public String toString() {
        return "SecurityRole{" + "id=" + id +
                ", authority='" + authority + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", defaultRole=" + defaultRole +
                '}';
    }
}
