package com.blue.project.modules.organizations.dao;

import com.blue.project.modules.organizations.models.Organizations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrganizationsRepository extends JpaRepository<Organizations, Long>, JpaSpecificationExecutor<Organizations> {
}
