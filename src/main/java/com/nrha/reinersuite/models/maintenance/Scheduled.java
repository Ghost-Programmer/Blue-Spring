package com.nrha.reinersuite.models.maintenance;

import com.nrha.reinersuite.models.AbstractTimestampEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Scheduled scheduled = (Scheduled) o;

        if (!Objects.equals(id, scheduled.id)) return false;
        if (!Objects.equals(description, scheduled.description))
            return false;
        if (!Objects.equals(start, scheduled.start)) return false;
        return Objects.equals(end, scheduled.end);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Scheduled{");
        sb.append("id=").append(id);
        sb.append(", description='").append(description).append('\'');
        sb.append(", start=").append(start);
        sb.append(", end=").append(end);
        sb.append('}');
        return sb.toString();
    }
}
