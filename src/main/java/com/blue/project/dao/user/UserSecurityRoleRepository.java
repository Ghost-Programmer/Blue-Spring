package com.blue.project.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.blue.project.models.users.UserSecurityRole;

import java.util.List;

public interface UserSecurityRoleRepository extends JpaRepository<UserSecurityRole, Long>, JpaSpecificationExecutor<UserSecurityRole> {

    List<UserSecurityRole> findAllByUserId(Long userId);

    UserSecurityRole findFirstByUser_IdAndSecurityRole_Id(Long userId, Long securityRoleId);
}
