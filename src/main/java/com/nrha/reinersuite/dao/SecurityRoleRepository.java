package com.nrha.reinersuite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.nrha.reinersuite.models.SecurityRole;

public interface SecurityRoleRepository extends JpaRepository<SecurityRole, Long>, JpaSpecificationExecutor<SecurityRole> {
    SecurityRole findFirstByAuthority(String authority);
}
