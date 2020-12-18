package com.nrha.reinersuite.service;

import com.nrha.reinersuite.dao.maintenance.AuditRowRepository;
import com.nrha.reinersuite.dao.maintenance.AuditTableRepository;
import com.nrha.reinersuite.models.maintenance.AuditRow;
import com.nrha.reinersuite.models.maintenance.AuditTable;
import com.nrha.reinersuite.models.maintenance.AuditTableType;
import name.mymiller.nadia.Nadia;
import name.mymiller.nadia.dto.EntityAudit;
import name.mymiller.nadia.interfaces.EntityAuditInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;

@Service
@Transactional
public class AuditService implements EntityAuditInterface {

    @Autowired
    private AuditTableRepository auditTableRepository;

    @Autowired
    private AuditRowRepository auditRowRepository;

    public AuditService() {
        Nadia.getInstance().setAuditInterface(this);
    }

    @Override
    public Object table(EntityAudit.Type type, String table, String user, String rowId, ZonedDateTime timestamp) {
        AuditTableType auditType = AuditTableType.UPDATE;
        switch(type) {
            case INSERT -> auditType = AuditTableType.INSERT;
            case UPDATE -> auditType = AuditTableType.UPDATE;
            case DELETE -> auditType = AuditTableType.DELETE;
        }
        return this.auditTableRepository.save(new AuditTable(auditType,table,user,rowId,timestamp));
    }

    @Override
    public void field(Object tableId, String field, String original, String changed) {
        AuditTable auditTable = null;
        if(tableId instanceof AuditTable) {
            auditTable = (AuditTable) tableId;
        } else {
            throw new IllegalArgumentException("tableId must be of type AuditTable");
        }
        this.auditRowRepository.save(new AuditRow(auditTable,field,original,changed));
    }
}
