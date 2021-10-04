package com.blue.project.modules.maintenance.models;

import com.blue.project.models.AbstractTimestampEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Table(name = "event", schema = "maintenance", catalog = "maintenance")
@Entity
public class MaintenanceEvent extends AbstractTimestampEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "event")
    private String event;

    public MaintenanceEvent() {
    }

    public MaintenanceEvent(String event) {
        this.event = event;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaintenanceEvent that = (MaintenanceEvent) o;
        return Objects.equals(id, that.id) && Objects.equals(event, that.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, event);
    }
}
