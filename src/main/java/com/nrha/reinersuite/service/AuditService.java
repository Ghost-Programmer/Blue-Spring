package com.nrha.reinersuite.service;

import name.mymiller.nadia.Nadia;
import name.mymiller.nadia.dto.EntityAudit;
import name.mymiller.nadia.interfaces.EntityAuditInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;

@Service
@Transactional
public class AuditService implements EntityAuditInterface {

    public AuditService() {
        Nadia.getInstance().setAuditInterface(this);
    }

    @Override
    public Long table(EntityAudit.Type type, String table, String user, String rowId, ZonedDateTime timestamp) {
        return null;
    }

    @Override
    public void field(Long tableId, String field, String original, String changed) {

    }
}
