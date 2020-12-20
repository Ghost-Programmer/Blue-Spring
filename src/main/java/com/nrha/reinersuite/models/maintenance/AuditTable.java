package com.nrha.reinersuite.models.maintenance;

import com.nrha.reinersuite.models.AbstractTimestampEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

@Table(name = "audit_table", schema = "nrha_maintenance", catalog = "nrha_maintenance")
@Entity
public class AuditTable extends AbstractTimestampEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private AuditTableType type;

    @Column(name = "table_name")
    private String tableName;

    @Column(name = "user")
    private String user;

    @Column(name = "row_id")
    private String row;

    @Column(name = "recorded")
    private ZonedDateTime timestamp;

    @OneToMany(mappedBy = "auditTable")
    private List<AuditRow> rows;

    public AuditTable() {
        super();
    }

    public AuditTable(AuditTableType type, String tableName, String user, String row, ZonedDateTime timestamp) {
        super();
        this.type = type;
        this.tableName = tableName;
        this.user = user;
        this.row = row;
        this.timestamp = timestamp;
        this.rows = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuditTableType getType() {
        return type;
    }

    public void setType(AuditTableType type) {
        this.type = type;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
