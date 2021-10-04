package com.blue.project.modules.users.dao;

import com.blue.project.modules.users.models.UserSecurityRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserSecurityRoleRepository extends JpaRepository<UserSecurityRole, Long>, JpaSpecificationExecutor<UserSecurityRole> {

    List<UserSecurityRole> findAllByUserId(Long userId);

    UserSecurityRole findFirstByUser_IdAndSecurityRole_Id(Long userId, Long securityRoleId);
}
