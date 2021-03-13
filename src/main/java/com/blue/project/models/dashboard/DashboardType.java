package com.blue.project.models.dashboard;

import com.blue.project.models.AbstractEntity;
import com.blue.project.models.users.SecurityRole;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "dashboard_type", schema = "dashboard", catalog = "dashboard")
@Entity
public class DashboardType extends AbstractEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "rowspan")
    private Integer rowspan;

    @Column(name = "colspan")
    private Integer colspan;

    @Column(name = "data")
    private String data;

    @Column(name = "default")
    private Boolean defaultItem;

    @ManyToOne
    @JoinColumn(name = "security_role_id")
    private SecurityRole role;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getDefaultItem() {
        return defaultItem;
    }

    public void setDefaultItem(Boolean defaultItem) {
        this.defaultItem = defaultItem;
    }

    public SecurityRole getRole() {
        return role;
    }

    public void setRole(SecurityRole role) {
        this.role = role;
    }

    public Integer getRowspan() {
        return rowspan;
    }

    public void setRowspan(Integer rowspan) {
        this.rowspan = rowspan;
    }

    public Integer getColspan() {
        return colspan;
    }

    public void setColspan(Integer colspan) {
        this.colspan = colspan;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DashboardType that = (DashboardType) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type) && Objects.equals(rowspan, that.rowspan) && Objects.equals(colspan, that.colspan) && Objects.equals(data, that.data) && Objects.equals(defaultItem, that.defaultItem) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, rowspan, colspan, data, defaultItem, role);
    }

    @Override
    public String toString() {
        return "DashboardType{" + "id=" + id +
                ", type='" + type + '\'' +
                ", rowspan=" + rowspan +
                ", colspan=" + colspan +
                ", data='" + data + '\'' +
                ", defaultItem=" + defaultItem +
                ", role=" + role +
                '}';
    }
}
