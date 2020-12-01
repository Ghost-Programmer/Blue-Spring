package com.nrha.reinersuite.models.maintenance;

import com.nrha.reinersuite.models.AbstractTimestampEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Table(name = "scheduled", schema = "nrha_maintenance", catalog = "nrha_maintenance")
@Entity
public class Scheduled extends AbstractTimestampEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "start")
    private ZonedDateTime start;

    @Column(name = "end")
    private ZonedDateTime end;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public ZonedDateTime getEnd() {
        return end;
    }

    public void setEnd(ZonedDateTime end) {
        this.end = end;
    }
}
