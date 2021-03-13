package com.blue.project.models.users;

import com.blue.project.models.AbstractTimestampEntity;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

@Table(name = "verification_token", schema = "user", catalog = "user")
@Entity
public class VerificationToken extends AbstractTimestampEntity implements Serializable {
    @Serial
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VerificationToken that = (VerificationToken) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(email, that.email)) return false;
        if (!Objects.equals(verification, that.verification)) return false;
        if (!Objects.equals(password, that.password)) return false;
        if (!Objects.equals(expiration, that.expiration)) return false;
        return Objects.equals(valid, that.valid);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (verification != null ? verification.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (expiration != null ? expiration.hashCode() : 0);
        result = 31 * result + (valid != null ? valid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VerificationToken{");
        sb.append("id=").append(id);
        sb.append(", email='").append(email).append('\'');
        sb.append(", verification='").append(verification).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", expiration=").append(expiration);
        sb.append(", valid=").append(valid);
        sb.append('}');
        return sb.toString();
    }
}
