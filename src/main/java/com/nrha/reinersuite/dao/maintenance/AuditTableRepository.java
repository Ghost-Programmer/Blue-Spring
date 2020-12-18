package com.nrha.reinersuite.dao.maintenance;

import com.nrha.reinersuite.models.maintenance.AuditTable;
import com.nrha.reinersuite.models.maintenance.Scheduled;
import com.nrha.reinersuite.models.users.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AuditTableRepository   extends JpaRepository<AuditTable, Long>, JpaSpecificationExecutor<AuditTable> {
}
