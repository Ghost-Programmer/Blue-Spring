package com.nrha.reinersuite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.nrha.reinersuite.models.users.SecurityRole;

import java.util.List;

public interface SecurityRoleRepository extends JpaRepository<SecurityRole, Long>, JpaSpecificationExecutor<SecurityRole> {
    SecurityRole findFirstByAuthority(String authority);

    List<SecurityRole> findAllByDefaultRoleTrue();
}
