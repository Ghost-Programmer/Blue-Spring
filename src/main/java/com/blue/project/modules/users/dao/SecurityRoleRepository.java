package com.blue.project.modules.users.dao;

import com.blue.project.modules.users.models.SecurityRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface SecurityRoleRepository extends JpaRepository<SecurityRole, Long>, JpaSpecificationExecutor<SecurityRole> {
    SecurityRole findFirstByAuthority(String authority);

    List<SecurityRole> findAllByDefaultRoleTrue();

}
