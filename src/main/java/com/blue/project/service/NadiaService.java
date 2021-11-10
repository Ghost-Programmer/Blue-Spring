package com.blue.project.service;

import com.blue.project.modules.maintenance.dao.AuditRowRepository;
import com.blue.project.modules.maintenance.dao.AuditTableRepository;
import com.blue.project.modules.maintenance.dao.MaintenanceEventRepository;
import com.blue.project.modules.maintenance.models.AuditRow;
import com.blue.project.modules.maintenance.models.AuditTable;
import com.blue.project.modules.maintenance.models.AuditTableType;
import com.blue.project.modules.maintenance.models.MaintenanceEvent;
import name.mymiller.nadia.Nadia;
import name.mymiller.nadia.dao.DaoEvent;
import name.mymiller.nadia.dto.EntityAudit;
import name.mymiller.nadia.interfaces.EntityAuditInterface;
import name.mymiller.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NadiaService implements EntityAuditInterface, DaoEvent {

    private final MaintenanceEventRepository maintenanceEventRepository;
    @Autowired
    private AuditTableRepository auditTableRepository;
    @Autowired
    private AuditRowRepository auditRowRepository;

    public NadiaService(MaintenanceEventRepository maintenanceEventRepository) {
        this.maintenanceEventRepository = maintenanceEventRepository;
        Nadia.getInstance().setAuditInterface(this);
        Nadia.getInstance().setDaoEvent(this);
        this.addNadiaEventPipelines();
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
    public void saveEvent(String event) {
        this.maintenanceEventRepository.save(new MaintenanceEvent(event));
    }

    @Override
    public List<String> readAllEvents() {
        return ListUtils.safe(this.maintenanceEventRepository.findAllByDateCreatedAfter(ZonedDateTime.now().minusWeeks(2))).stream()
                .map(MaintenanceEvent::getEvent).collect(Collectors.toList());
    }

    private void addNadiaEventPipelines() {
        Nadia.getInstance();
        //TODO: Insert code to add Nadia Event Pipelines here
    }
}
