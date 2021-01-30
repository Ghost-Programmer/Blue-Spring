package com.nrha.reinersuite.models.dashboard;

import com.nrha.reinersuite.models.AbstractEntity;
import com.nrha.reinersuite.models.users.SecurityRole;

import javax.persistence.*;
import java.io.Serial;
import java.util.Objects;

@Table(name = "dashboard_type", schema = "nrha_dashboard", catalog = "nrha_dashboard")
@Entity
public class DashboardType extends AbstractEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DashboardType that = (DashboardType) o;
        return Objects.equals(id, that.id) && Objects.equals(type, that.type) && Objects.equals(defaultItem, that.defaultItem) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, defaultItem, role);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DashboardType{");
        sb.append("id=").append(id);
        sb.append(", type='").append(type).append('\'');
        sb.append(", defaultItem=").append(defaultItem);
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }
}
