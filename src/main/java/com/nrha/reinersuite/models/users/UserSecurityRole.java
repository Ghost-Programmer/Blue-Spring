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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserSecurityRole that = (UserSecurityRole) o;

    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (user != null ? !user.equals(that.user) : that.user != null) return false;
    return securityRole != null ? securityRole.equals(that.securityRole) : that.securityRole == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (user != null ? user.hashCode() : 0);
    result = 31 * result + (securityRole != null ? securityRole.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("UserSecurityRole{");
    sb.append("id=").append(id);
    sb.append(", user=").append(user);
    sb.append(", securityRole=").append(securityRole);
    sb.append('}');
    return sb.toString();
  }
}
