package com.nrha.reinersuite.models;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;

@Table(name = "verification_token")
@Entity
public class VerificationToken extends AbstractTimestampEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "verification", nullable = false)
  private String verification;

  @Column(name = "password")
  private String password;

  @Column(name = "expiration")
  private ZonedDateTime expiration;

  @Column(name = "valid")
  private Boolean valid = Boolean.FALSE;


  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getVerification() {
    return verification;
  }

  public void setVerification(String verification) {
    this.verification = verification;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public ZonedDateTime getExpiration() {
    return expiration;
  }

  public void setExpiration(ZonedDateTime expiration) {
    this.expiration = expiration;
  }

  public Boolean getValid() {
    return valid;
  }

  public void setValid(Boolean valid) {
    this.valid = valid;
  }
}
