package com.blue.project.models;

import com.blue.project.View.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@MappedSuperclass
public abstract class AbstractTimestampEntity extends AbstractEntity {

    @Column(name = "date_created", nullable = false, updatable = false)
    @JsonView(Views.Hidden.class)
    private ZonedDateTime dateCreated;

    @JsonIgnore
    @Column(name = "last_updated", nullable = false)
    private ZonedDateTime lastUpdated;

    public AbstractTimestampEntity() {
    }

    @PrePersist
    protected void onCreate() {
        this.dateCreated = this.lastUpdated = ZonedDateTime.now(ZoneOffset.UTC);
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdated = ZonedDateTime.now(ZoneOffset.UTC);
    }

    @SuppressWarnings("unused")
    public ZonedDateTime getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @SuppressWarnings("unused")
    public ZonedDateTime getLastUpdated() {
        return this.lastUpdated;
    }

}
