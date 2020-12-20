package com.nrha.reinersuite.models.users;

import com.nrha.reinersuite.models.AbstractTimestampEntity;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "user", schema = "nrha_user", catalog = "nrha_user")
@Entity
public class User extends AbstractTimestampEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  @JsonIgnore
  private String password;

  @Column(name = "enabled", nullable = false)
  private Boolean enabled = Boolean.FALSE;

  @Column(name = "account_expired")
  private Boolean accountExpired;

  @Column(name = "password_change_required", nullable = false)
  private Boolean passwordChangeRequired = Boolean.FALSE;

  @Column(name = "account_locked")
  private Boolean accountLocked;

  @Column(name = "account_verified", nullable = false)
  private Boolean accountVerified = Boolean.FALSE;

  @Column(name = "account_complete", nullable = false)
  private Boolean accountComplete = Boolean.FALSE;

  @Column(name = "account_refreshed", nullable = false)
  private Boolean accountRefreshed = Boolean.FALSE;

  public static User from(String username) {
    User user = new User();
    user.setUsername(username);
    user.setEnabled(null);
    user.setPassword(null);
    user.setId(null);
    user.setAccountComplete(null);
    user.setAccountExpired(null);
    user.setAccountLocked(null);
    user.setAccountRefreshed(null);
    user.setAccountVerified(null);
    user.setPasswordChangeRequired(null);
    user.setDateCreated(null);

    return user;
  }

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public Boolean getAccountExpired() {
    return accountExpired;
  }

  public void setAccountExpired(Boolean accountExpired) {
    this.accountExpired = accountExpired;
  }

  public Boolean getPasswordChangeRequired() {
    return passwordChangeRequired;
  }

  public void setPasswordChangeRequired(Boolean passwordChangeRequired) {
    this.passwordChangeRequired = passwordChangeRequired;
  }

  public Boolean getAccountLocked() {
    return accountLocked;
  }

  public void setAccountLocked(Boolean accountLocked) {
    this.accountLocked = accountLocked;
  }

  public Boolean getAccountVerified() {
    return accountVerified;
  }

  public void setAccountVerified(Boolean accountVerified) {
    this.accountVerified = accountVerified;
  }

  public Boolean getAccountComplete() {
    return accountComplete;
  }

  public void setAccountComplete(Boolean accountComplete) {
    this.accountComplete = accountComplete;
  }

  public Boolean getAccountRefreshed() {
    return accountRefreshed;
  }

  public void setAccountRefreshed(Boolean accountRefreshed) {
    this.accountRefreshed = accountRefreshed;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (id != null ? !id.equals(user.id) : user.id != null) return false;
    if (username != null ? !username.equals(user.username) : user.username != null) return false;
    if (password != null ? !password.equals(user.password) : user.password != null) return false;
    if (enabled != null ? !enabled.equals(user.enabled) : user.enabled != null) return false;
    if (accountExpired != null ? !accountExpired.equals(user.accountExpired) : user.accountExpired != null)
      return false;
    if (passwordChangeRequired != null ? !passwordChangeRequired.equals(user.passwordChangeRequired) : user.passwordChangeRequired != null)
      return false;
    if (accountLocked != null ? !accountLocked.equals(user.accountLocked) : user.accountLocked != null) return false;
    if (accountVerified != null ? !accountVerified.equals(user.accountVerified) : user.accountVerified != null)
      return false;
    if (accountComplete != null ? !accountComplete.equals(user.accountComplete) : user.accountComplete != null)
      return false;
    return accountRefreshed != null ? accountRefreshed.equals(user.accountRefreshed) : user.accountRefreshed == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (username != null ? username.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
    result = 31 * result + (accountExpired != null ? accountExpired.hashCode() : 0);
    result = 31 * result + (passwordChangeRequired != null ? passwordChangeRequired.hashCode() : 0);
    result = 31 * result + (accountLocked != null ? accountLocked.hashCode() : 0);
    result = 31 * result + (accountVerified != null ? accountVerified.hashCode() : 0);
    result = 31 * result + (accountComplete != null ? accountComplete.hashCode() : 0);
    result = 31 * result + (accountRefreshed != null ? accountRefreshed.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("User{");
    sb.append("id=").append(id);
    sb.append(", username='").append(username).append('\'');
    sb.append(", password='").append(password).append('\'');
    sb.append(", enabled=").append(enabled);
    sb.append(", accountExpired=").append(accountExpired);
    sb.append(", passwordChangeRequired=").append(passwordChangeRequired);
    sb.append(", accountLocked=").append(accountLocked);
    sb.append(", accountVerified=").append(accountVerified);
    sb.append(", accountComplete=").append(accountComplete);
    sb.append(", accountRefreshed=").append(accountRefreshed);
    sb.append('}');
    return sb.toString();
  }
}
