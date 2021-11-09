package com.blue.project.modules.maintenance.dto;

import com.blue.project.modules.maintenance.models.Scheduled;

public class EventData extends com.blue.project.modules.calendar.dto.EventData {
    public EventData(Scheduled scheduled) {
        this.setStartDate(scheduled.getStart());
        this.setEndDate(scheduled.getEnd());
        this.setTitle("System Maintenance: " + scheduled.getDescription());
        this.setDesc(null);
        this.setCreatedAt(scheduled.getDateCreated());
    }
}
