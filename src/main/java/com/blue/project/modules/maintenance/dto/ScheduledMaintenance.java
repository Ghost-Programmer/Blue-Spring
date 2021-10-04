package com.blue.project.modules.maintenance.dto;

import com.blue.project.modules.maintenance.models.Scheduled;

import java.time.ZonedDateTime;

public class ScheduledMaintenance {

    private ZonedDateTime start;
    private ZonedDateTime end;
    private String description;

    public ScheduledMaintenance(Scheduled scheduled) {
        this.start = scheduled.getStart();
        this.end = scheduled.getEnd();
        this.description = scheduled.getDescription();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
