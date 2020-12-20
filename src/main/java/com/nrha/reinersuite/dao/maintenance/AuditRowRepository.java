package com.nrha.reinersuite.dao.maintenance;

import com.nrha.reinersuite.models.maintenance.AuditRow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuditRowRepository  extends JpaRepository<AuditRow, Long>, JpaSpecificationExecutor<AuditRow> {
}
