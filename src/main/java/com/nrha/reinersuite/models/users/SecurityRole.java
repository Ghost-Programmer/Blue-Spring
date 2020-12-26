package com.nrha.reinersuite.models.users;

import com.nrha.reinersuite.models.AbstractTimestampEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "security_role", schema = "nrha_user", catalog = "nrha_user")
public class SecurityRole extends AbstractTimestampEntity implements Serializable {

  public static final String ROLE_USER = "ROLE_USER";
  public static final String ROLE_USER_MANAGEMENT = "ROLE_USER_MANAGEMENT";
  public static final String ROLE_CHANGE_USER_PASSWORD = "ROLE_CHANGE_USER_PASSWORD";
  public static final String ROLE_CHANGE_OWN_PASSWORD = "ROLE_CHANGE_OWN_PASSWORD";
  public static final String ROLE_DEVELOPER = "ROLE_DEVELOPER";

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
    final StringBuilder sb = new StringBuilder("SecurityRole{");
    sb.append("id=").append(id);
    sb.append(", authority='").append(authority).append('\'');
    sb.append(", name='").append(name).append('\'');
    sb.append(", category='").append(category).append('\'');
    sb.append(", description='").append(description).append('\'');
    sb.append(", defaultRole=").append(defaultRole);
    sb.append('}');
    return sb.toString();
  }
}
