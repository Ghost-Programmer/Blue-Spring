package com.blue.project.models.dashboard;

import com.blue.project.models.AbstractEntity;

import javax.persistence.*;
import java.io.Serial;
import java.util.Objects;

@Table(name = "dashboard", schema = "dashboard", catalog = "dashboard")
@Entity
public class Dashboard extends AbstractEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "rowspan")
    private Integer rowspan;

    @Column(name = "colspan")
    private Integer colspan;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "data")
    private String data;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private DashboardType type;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public DashboardType getType() {
        return type;
    }

    public void setType(DashboardType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dashboard dashboard = (Dashboard) o;
        return userId == dashboard.userId && Objects.equals(id, dashboard.id) && Objects.equals(rowspan, dashboard.rowspan) && Objects.equals(colspan, dashboard.colspan) && Objects.equals(sort, dashboard.sort) && Objects.equals(data, dashboard.data) && Objects.equals(type, dashboard.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, rowspan, colspan, sort, data, type);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Dashboard{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", rowspan=").append(rowspan);
        sb.append(", colspan=").append(colspan);
        sb.append(", sort=").append(sort);
        sb.append(", data='").append(data).append('\'');
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
