package com.nrha.reinersuite.models.users;

import com.nrha.reinersuite.models.AbstractTimestampEntity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "user_security_role", schema = "nrha_user", catalog = "nrha_user")
public class UserSecurityRole extends AbstractTimestampEntity implements Serializable {
  private static final long serialVersionUID = 1L;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private User user;

  @ManyToOne
  private SecurityRole securityRole;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public SecurityRole getSecurityRole() {
    return securityRole;
  }

  public void setSecurityRole(SecurityRole securityRole) {
    this.securityRole = securityRole;
  }
}
