package com.blue.project.modules.users.dao;

import com.blue.project.modules.users.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>, JpaSpecificationExecutor<VerificationToken> {

    VerificationToken findFirstByVerification(String verification);

    Boolean existsByEmail(String email);

    VerificationToken findFirstByEmail(String email);
}
