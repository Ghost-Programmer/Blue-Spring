package com.nrha.reinersuite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.nrha.reinersuite.models.UserSecurityRole;

import java.util.List;

public interface UserSecurityRoleRepository extends JpaRepository<UserSecurityRole, Long>, JpaSpecificationExecutor<UserSecurityRole> {

    List<UserSecurityRole> findAllByUserId(Long userId);
}
