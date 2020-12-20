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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuditTable that = (AuditTable) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (type != that.type) return false;
        if (tableName != null ? !tableName.equals(that.tableName) : that.tableName != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (row != null ? !row.equals(that.row) : that.row != null) return false;
        if (timestamp != null ? !timestamp.equals(that.timestamp) : that.timestamp != null) return false;
        return rows != null ? rows.equals(that.rows) : that.rows == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (tableName != null ? tableName.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (row != null ? row.hashCode() : 0);
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (rows != null ? rows.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AuditTable{");
        sb.append("id=").append(id);
        sb.append(", type=").append(type);
        sb.append(", tableName='").append(tableName).append('\'');
        sb.append(", user='").append(user).append('\'');
        sb.append(", row='").append(row).append('\'');
        sb.append(", timestamp=").append(timestamp);
        sb.append(", rows=").append(rows);
        sb.append('}');
        return sb.toString();
    }
}
