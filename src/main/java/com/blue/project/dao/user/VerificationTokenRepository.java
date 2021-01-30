package com.blue.project.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.blue.project.models.users.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>, JpaSpecificationExecutor<VerificationToken> {

    VerificationToken findFirstByVerification(String verification);
    Boolean existsByEmail(String email);
    VerificationToken findFirstByEmail(String email);
}
