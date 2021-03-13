package com.blue.project.dao.maintenance;

import com.blue.project.models.maintenance.AuditRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuditRowRepository extends JpaRepository<AuditRow, Long>, JpaSpecificationExecutor<AuditRow> {
}
