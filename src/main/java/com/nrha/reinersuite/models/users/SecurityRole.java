package com.nrha.reinersuite.models.users;

import com.nrha.reinersuite.models.AbstractTimestampEntity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "security_role", schema = "nrha_user", catalog = "nrha_user")
public class SecurityRole extends AbstractTimestampEntity implements Serializable {

  public static final String ROLE_USER = "ROLE_USER";
  public static final String ROLE_USER_MANAGEMENT = "ROLE_USER_MANAGEMENT";
  public static final String ROLE_CHANGE_USER_PASSWORD = "ROLE_CHANGE_USER_PASSWORD";
  public static final String ROLE_CHANGE_OWN_PASSWORD = "ROLE_CHANGE_OWN_PASSWORD";

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
}
