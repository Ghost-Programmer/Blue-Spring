package com.blue.project.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.blue.project.models.users.SecurityRole;

import java.util.List;

public interface SecurityRoleRepository extends JpaRepository<SecurityRole, Long>, JpaSpecificationExecutor<SecurityRole> {
    SecurityRole findFirstByAuthority(String authority);

    List<SecurityRole> findAllByDefaultRoleTrue();

}
