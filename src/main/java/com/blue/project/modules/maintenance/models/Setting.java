package com.blue.project.modules.maintenance.models;

import com.blue.project.models.AbstractTimestampEntity;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Setting settings = (Setting) o;
        return Objects.equals(id, settings.id) && Objects.equals(setting, settings.setting) && Objects.equals(value, settings.value) && Objects.equals(startDateTime, settings.startDateTime) && Objects.equals(endDateTime, settings.endDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, setting, value, startDateTime, endDateTime);
    }
}
