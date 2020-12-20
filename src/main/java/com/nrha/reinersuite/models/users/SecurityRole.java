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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SecurityRole that = (SecurityRole) o;

    if (!Objects.equals(id, that.id)) return false;
    if (!Objects.equals(authority, that.authority)) return false;
    if (!Objects.equals(name, that.name)) return false;
    if (!Objects.equals(description, that.description)) return false;
    return Objects.equals(defaultRole, that.defaultRole);
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (authority != null ? authority.hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (defaultRole != null ? defaultRole.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("SecurityRole{");
    sb.append("id=").append(id);
    sb.append(", authority='").append(authority).append('\'');
    sb.append(", name='").append(name).append('\'');
    sb.append(", description='").append(description).append('\'');
    sb.append(", defaultRole=").append(defaultRole);
    sb.append('}');
    return sb.toString();
  }
}
