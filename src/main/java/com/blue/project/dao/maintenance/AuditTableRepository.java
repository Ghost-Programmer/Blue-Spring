package com.blue.project.dao.maintenance;

import com.blue.project.models.maintenance.AuditTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuditTableRepository   extends JpaRepository<AuditTable, Long>, JpaSpecificationExecutor<AuditTable> {
}
