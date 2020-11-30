package com.nrha.reinersuite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.nrha.reinersuite.models.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>, JpaSpecificationExecutor<VerificationToken> {

    VerificationToken findFirstByVerification(String verification);
    Boolean existsByEmail(String email);
    VerificationToken findFirstByEmail(String email);
}
