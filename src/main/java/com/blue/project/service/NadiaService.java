package com.blue.project.service;

import com.blue.project.modules.maintenance.dao.AuditRowRepository;
import com.blue.project.modules.maintenance.dao.AuditTableRepository;
import com.blue.project.modules.maintenance.models.AuditRow;
import com.blue.project.modules.maintenance.models.AuditTable;
import com.blue.project.modules.maintenance.models.AuditTableType;
import name.mymiller.nadia.Nadia;
import name.mymiller.nadia.dao.DaoEvent;
import name.mymiller.nadia.dto.EntityAudit;
import name.mymiller.nadia.interfaces.EntityAuditInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@Transactional
public class NadiaService implements EntityAuditInterface, DaoEvent {

    @Autowired
    private AuditTableRepository auditTableRepository;

    @Autowired
    private AuditRowRepository auditRowRepository;

    public NadiaService() {
        Nadia.getInstance().setAuditInterface(this);
    }

    @Override
    public Object table(EntityAudit.Type type, String table, String user, String rowId, ZonedDateTime timestamp) {
        AuditTableType auditType;
        switch (type) {
            default -> auditType = AuditTableType.UPDATE;
            case INSERT -> auditType = AuditTableType.INSERT;
            case DELETE -> auditType = AuditTableType.DELETE;
        }
        return this.auditTableRepository.save(new AuditTable(auditType, table, user, rowId, timestamp));
    }

    @Override
    public void field(Object tableId, String field, String original, String changed) {
        AuditTable auditTable;
        if (tableId instanceof AuditTable) {
            auditTable = (AuditTable) tableId;
        } else {
            throw new IllegalArgumentException("tableId must be of type AuditTable");
        }
        this.auditRowRepository.save(new AuditRow(auditTable, field, original, changed));
    }

    @Override
    public void saveEvent(String s) {

    }

    @Override
    public List<String> readAllEvents() {
        return null;
    }
}
