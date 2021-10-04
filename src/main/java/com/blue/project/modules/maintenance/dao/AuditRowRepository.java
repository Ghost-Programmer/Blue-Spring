package com.blue.project.modules.maintenance.dao;

import com.blue.project.modules.maintenance.models.AuditRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuditRowRepository extends JpaRepository<AuditRow, Long>, JpaSpecificationExecutor<AuditRow> {
}
