package com.blue.project.modules.maintenance.models;

import com.blue.project.models.AbstractTimestampEntity;
import com.blue.project.modules.organizations.models.Organizations;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

@Table(name = "settings", schema = "maintenance", catalog = "maintenance")
@Entity
public class Setting extends AbstractTimestampEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "setting")
    private String setting;

    @ManyToOne
    @JoinColumn(name = "organization")
    private Organizations organization;

    @Column(name = "value")
    private String value;

    @Column(name = "start_date_time")
    private ZonedDateTime startDateTime;

    @Column(name = "end_date_time")
    private ZonedDateTime endDateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(ZonedDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public ZonedDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(ZonedDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Organizations getOrganization() {
        return organization;
    }

    public void setOrganization(Organizations organization) {
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Setting setting1 = (Setting) o;
        return Objects.equals(id, setting1.id) && Objects.equals(setting, setting1.setting) && Objects.equals(organization, setting1.organization) && Objects.equals(value, setting1.value) && Objects.equals(startDateTime, setting1.startDateTime) && Objects.equals(endDateTime, setting1.endDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, setting, organization, value, startDateTime, endDateTime);
    }
}
