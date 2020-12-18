package com.nrha.reinersuite.models.maintenance;

import com.nrha.reinersuite.models.AbstractEntity;
import com.nrha.reinersuite.models.AbstractTimestampEntity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "audit_row", schema = "nrha_maintenance", catalog = "nrha_maintenance")
@Entity
public class AuditRow extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "audit_table_id", nullable = false)
    private AuditTable auditTable;

    @Column(name = "field")
    private String field;

    @Column(name = "original")
    private String original;

    @Column(name = "change")
    private String change;

    public AuditRow() {
    }

    public AuditRow( AuditTable auditTable, String field, String original, String change) {
        this.id = id;
        this.auditTable = auditTable;
        this.field = field;
        this.original = original;
        this.change = change;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuditTable getAuditTable() {
        return auditTable;
    }

    public void setAuditTable(AuditTable auditTable) {
        this.auditTable = auditTable;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }
}
