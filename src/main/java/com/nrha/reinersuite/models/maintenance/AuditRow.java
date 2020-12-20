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

    @Column(name = "new_value")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuditRow auditRow = (AuditRow) o;

        if (id != null ? !id.equals(auditRow.id) : auditRow.id != null) return false;
        if (auditTable != null ? !auditTable.equals(auditRow.auditTable) : auditRow.auditTable != null) return false;
        if (field != null ? !field.equals(auditRow.field) : auditRow.field != null) return false;
        if (original != null ? !original.equals(auditRow.original) : auditRow.original != null) return false;
        return change != null ? change.equals(auditRow.change) : auditRow.change == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (auditTable != null ? auditTable.hashCode() : 0);
        result = 31 * result + (field != null ? field.hashCode() : 0);
        result = 31 * result + (original != null ? original.hashCode() : 0);
        result = 31 * result + (change != null ? change.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuditRow{");
        sb.append("id=").append(id);
        sb.append(", auditTable=").append(auditTable);
        sb.append(", field='").append(field).append('\'');
        sb.append(", original='").append(original).append('\'');
        sb.append(", change='").append(change).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
