package com.blue.project.modules.maintenance.dao;

import com.blue.project.modules.maintenance.models.AuditTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuditTableRepository extends JpaRepository<AuditTable, Long>, JpaSpecificationExecutor<AuditTable> {
}
