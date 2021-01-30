package com.blue.project.models.maintenance;

import com.blue.project.models.AbstractTimestampEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@Table(name = "audit_table", schema = "maintenance", catalog = "maintenance")
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

        if (!Objects.equals(id, that.id)) return false;
        if (type != that.type) return false;
        if (!Objects.equals(tableName, that.tableName)) return false;
        if (!Objects.equals(user, that.user)) return false;
        if (!Objects.equals(row, that.row)) return false;
        if (!Objects.equals(timestamp, that.timestamp)) return false;
        return Objects.equals(rows, that.rows);
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
