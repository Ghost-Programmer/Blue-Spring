package com.blue.project.modules.calendar.dto;

import java.util.List;

public class EventDay {
    private Integer date;
    private Integer month;
    private Integer year;
    private List<EventData> events;

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<EventData> getEvents() {
        return events;
    }

    public void setEvents(List<EventData> events) {
        this.events = events;
    }
}
